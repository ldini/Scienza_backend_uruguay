package ar.com.scienza.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.io.Files;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.NotificationDeleteRequestDto;
import ar.com.scienza.dto.NotificationMessageFilterRequestDto;
import ar.com.scienza.dto.NotificationMessageResultResponseDto;
import ar.com.scienza.dto.NotificationMessageSendRequestDto;
import ar.com.scienza.dto.NotificationMessageSentAffiliateDto;
import ar.com.scienza.dto.NotificationMessageSentDto;
import ar.com.scienza.dto.NotificationMessageSentListDto;
import ar.com.scienza.dto.NotificationResponseDto;
import ar.com.scienza.dto.NotificationResultResponseDto;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Mensaje;
import ar.com.scienza.entity.Notificacion;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.repository.MensajeRepository;
import ar.com.scienza.repository.NotificacionCriteriaQuery;
import ar.com.scienza.repository.NotificacionRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.INotificationService;

import javax.xml.bind.DatatypeConverter;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class NotificationService implements INotificationService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private NotificacionRepository notificacionRepository;

	@Autowired
	private NotificacionCriteriaQuery notificacionCriteriaQuery;

	@Value("${server.contextPath}")
	String contextPath;

	@Value("${location.files}")
	String locationFiles;

	@Value("${path.temporal}")
	String pathTemporal;

	@Override
	public void createNotification(
		Administrador administrador, 
		List<Afiliado> afiliadoList, 
		String title, 
		String pushMessage, 
		String message, 
		TipoNotificacionEnum type,
		Integer entityId
	) throws ServiceException {
		
		try 
		{			
			Mensaje mensaje = new Mensaje();
			mensaje.setAdministrador(administrador);
			mensaje.setTitulo(title);
			mensaje.setMensajePush(pushMessage);
			mensaje.setMensajeAbreviado(this.abbreviateMessage(message));
			mensaje.setMensaje(message);
			mensaje.createOnRepository(mensajeRepository);
			
			for(Afiliado afiliado : afiliadoList)
			{
				Notificacion notificacion = new Notificacion();
				notificacion.setAfiliado(afiliado);
				notificacion.setTipoNotificacion(type.getCodigo());
				notificacion.setMensaje(mensaje);
				notificacion.setNotificado(false);
				notificacion.setLeido(false);
				notificacion.setEntityId(entityId);
				notificacion.createOnRepository(notificacionRepository);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al generar la notificación");
		}
	}
	

	@Override
	public List<NotificationResultResponseDto> getNotificationList(String token, Long sapId, String deviceType) throws ServiceException {

		List<NotificationResultResponseDto> responseList = new ArrayList<NotificationResultResponseDto>();
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			List<Notificacion> notificacionList = notificacionRepository.findByAfiliadoIdAndFechaDeleteIsNullOrderByFechaInsertDesc(afiliado.getId());
			for(Notificacion notificacion : notificacionList)
			{
				NotificationResultResponseDto response = new NotificationResultResponseDto();
				response.setNotificationId(notificacion.getId());
				response.setTitulo(notificacion.processTitulo());
				response.setMensaje(notificacion.processMensajeAbreviado());
				response.setFechaNotificacion(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(notificacion.getFechaInsert()));
				response.setNuevo(!notificacion.getLeido());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener notificaciones");
		}
		
		return responseList;
	}


	@Override
	public NotificationResponseDto getNotification(Integer notificationId, String token, Long sapId, String deviceType, String version) throws ServiceException {

		NotificationResponseDto response = new NotificationResponseDto();
		
		try 
		{
			Notificacion notificacion = notificacionRepository.findOne(notificationId);
			response.setNotificationId(notificacion.getId());
			response.setTitulo(notificacion.processTitulo());
			response.setMensaje(notificacion.processMensaje());
			response.setFechaNotificacion(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(notificacion.getFechaInsert()));
			
			if("".equals(Strings.nullToEmpty(version)) && !"WEB".equals(deviceType)) {
	        	response.setMensaje(response.getMensaje().replace("pedido:", ""));
	        }			
			if(!notificacion.getLeido())
			{
				notificacion.setLeido(true);
				notificacion.setFechaLeido(new Date());
				notificacion.updateOnRepository(notificacionRepository);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener la notificación");
		}
		
		return response;
	}

	@Override
	public void deleteNotification(NotificationDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try
		{
			Sesion sesion = sesionRepository.findByToken(token);
			Afiliado afiliado = sesion.getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			this.deleteNotificationInDB(request);
		}
		catch(ServiceException e) {
			throw e;
		}
		catch(Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al eliminar la notificacion del afiliado.");
		}
	}

	private void deleteNotificationInDB(NotificationDeleteRequestDto request) throws Exception, ServiceException{
		Notificacion notificacion = notificacionRepository.findOne(request.getNotificacionId());
		if (notificacion == null) {
			throw new ServiceException("La notificacion " + request.getNotificacionId() + " no existe.");
		}

		if (notificacion.isDeleted()){
			throw new ServiceException("La notificacion " + request.getNotificacionId() + " ya ha sido borrada.");
		}

		notificacion.deleteOnRepository(notificacionRepository);

	}

	@Override
	public List<NotificationMessageResultResponseDto> getMessageSenderList(
		NotificationMessageFilterRequestDto request, 
		String token, 
		Long sapId, 
		String deviceType
	) throws ServiceException {

		List<NotificationMessageResultResponseDto> responseList = new ArrayList<NotificationMessageResultResponseDto>();
		
		try 
		{
			List<Afiliado> afiliadoList = notificacionCriteriaQuery.findAllMessageSenderByFilters(
											 request.getNombreApellidoList(), 
											 request.getSeguroMedicoList(),
											 request.getcedulaIdentidadList(),
											 request.getSexoList(),
											 request.getFechaNacimiento(),
											 request.getLocalidadProvinciaList(),
											 request.getTelefonoList(),
											 request.getCompaniaCelularList(),
											 request.getEmailList()
										  );
			
			for(Afiliado afiliado : afiliadoList)
			{
				NotificationMessageResultResponseDto response = new NotificationMessageResultResponseDto();
				response.setAfiliadoId(afiliado.getId());
				response.setNombre(afiliado.getNombre());
				response.setApellido(afiliado.getApellido());
				response.setcedulaIdentidad(afiliado.getcedulaIdentidad());
				response.setSapId(afiliado.getSapId());
				response.setSeguroMedico(afiliado.getSeguroMedico().getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener los destinatarios");
		}
		
		return responseList;
	}


	@Override
	public void sendNotification(NotificationMessageSendRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();

			List<Afiliado> afiliadoList = request.getMassive() ?
					this.getAffiliateListFromExcel(request) :
					afiliadoRepository.findAll(request.getAfiliadoIdList());

			if(afiliadoList.size() == 0) {
				throw new ServiceException("No hay ID de afiliados para procesar");
			}

			this.createNotification(
				administrador,
				afiliadoList,
				request.getTitulo(), 
				"${afiliado.nombre}, ha recibido una notificación de Scienza Móvil.", 
				request.getMensaje(), 
				TipoNotificacionEnum.MENSAJE_ADMIN,
				null
			);
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al enviar notificaciones");
		}
	}

	private List<Afiliado> getAffiliateListFromExcel(NotificationMessageSendRequestDto request) throws ServiceException {
		List<Afiliado> afiliadoList = new ArrayList<Afiliado>();
		Workbook workbook = null;

		try
		{
			String docBase64 = request.getContent().split(",")[1];
			String fileName    = request.getFileName();

			byte[] docBytes = DatatypeConverter.parseBase64Binary(docBase64);
			File docFile = new File(locationFiles + pathTemporal + fileName);
			Files.write(docBytes, docFile);

			workbook = WorkbookFactory.create(docFile);
			Sheet sheet = workbook.getSheetAt(0);

			this.validateHeader(sheet);

			int rowIndex = 0;
			for(rowIndex=1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++)
			{
				Row row = sheet.getRow(rowIndex);
				if(row == null) {
					continue;
				}

				Cell cell = row.getCell(0);
				if(cell == null) {
					continue;
				}

				Long afiliadoId = null;
				if(cell.getCellTypeEnum() == CellType.NUMERIC)
				{
					afiliadoId = (long) cell.getNumericCellValue();
				}
				else if(cell.getCellTypeEnum() == CellType.STRING) {
					try
					{
						afiliadoId = Long.valueOf(cell.getStringCellValue());
					}
					catch(NumberFormatException e) {
						continue;
					}
				}

				if(afiliadoId != null)
				{
					Afiliado afiliado = afiliadoRepository.findBySapId(afiliadoId);
					if(afiliado != null) {
						afiliadoList.add(afiliado);
					}
				}
			}
		}
		catch (ServiceException e) {
			ScienzaLogger.logError(e);
			throw e;
		}
		catch (Exception e)
		{
			ScienzaLogger.logError(e);
			throw new ServiceException(e.getMessage());
		}
		finally
		{
			try
			{
				workbook.close();
			}
			catch (IOException e)  {
				/* do-nothing */
			}
		}

		return afiliadoList;
	}

	private void validateHeader(Sheet sheet) throws ServiceException {
		Row row = sheet.getRow(0);

		if(!row.getCell(0).getStringCellValue().equals("ID PACIENTE"))
			throw new ServiceException("La cabecera debe contener el nombre ID PACIENTE. Valor obtenido: " + row.getCell(0).getStringCellValue());
	}

	/**
	 * Genera un mensaje abreviado para visualizar en la lista de notificaciones
	 * @param message
	 * @return
	 */
	private String abbreviateMessage(String message) {

		int messageLength = 100;
				
		Document document = Jsoup.parseBodyFragment("<div>" + message + "</div>");
		
		Element element = document.select("div p").first();
		if(element == null) {
			element = document.select("div").first();
		}
		String messageAux = element.text();
		
		if(messageAux.length() <= messageLength)
			return messageAux;
		
		return messageAux.substring(0, 97) + "..";
	}

	@Override
	public List<NotificationMessageSentListDto> getSentNotificationsHistory(String token, Long sapId, String deviceType) throws ServiceException {

		List<NotificationMessageSentListDto> responseList = new ArrayList<NotificationMessageSentListDto>();

		try
		{
			for (Mensaje mensaje : mensajeRepository.findAllByTipoNotificacion("MENSAJE_ADMIN")){

				NotificationMessageSentListDto notificationMessageSentListDto = new NotificationMessageSentListDto();
				notificationMessageSentListDto.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(mensaje.getFechaInsert()));
				notificationMessageSentListDto.setTitle(mensaje.getTitulo());
				notificationMessageSentListDto.setAdministratorName(mensaje.getAdministrador().getNombre());
				notificationMessageSentListDto.setAdministratorLastname(mensaje.getAdministrador().getApellido());
				notificationMessageSentListDto.setReceiversAmount(notificacionRepository.countByMensajeIdAndFechaDeleteIsNull(mensaje.getId()));
				notificationMessageSentListDto.setMessageId(mensaje.getId());
				responseList.add(notificationMessageSentListDto);

			}

		}catch (Exception ex){
			ScienzaLogger.logError(ex);
			throw new ServiceException("Error al obtener las notificaciones enviadas.");
		}

		return responseList;

	}

	@Override
	public NotificationMessageSentDto getSentNotificationDetail(Integer idMessage, String token, Long sapId, String deviceType, String version) throws ServiceException {

		NotificationMessageSentDto response = new NotificationMessageSentDto();

		try
		{

			Mensaje mensaje = mensajeRepository.findOne(idMessage);

			response.setTitle(mensaje.getTitulo());
			response.setMessage(mensaje.getMensaje());

			List<Afiliado> receivers = afiliadoRepository.findAllByMensaje(idMessage);

			List<NotificationMessageSentAffiliateDto> receiversNames = new ArrayList<NotificationMessageSentAffiliateDto>();

			for(Afiliado afiliado : receivers){

				NotificationMessageSentAffiliateDto affiliateName = new NotificationMessageSentAffiliateDto();

				affiliateName.setFirstName(afiliado.getNombre());
				affiliateName.setLastName(afiliado.getApellido());

				receiversNames.add(affiliateName);

			}

			response.setAffiliateNamesList(receiversNames);
		}
		catch (Exception ex){
			ScienzaLogger.logError(ex);
			throw new ServiceException("Error al obtener el detalle de la notificacion enviada.");
		}

		return response;
	}
}