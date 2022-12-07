package ar.com.scienza.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import ar.com.scienza.config.FreeMarkerConfig;
import ar.com.scienza.dto.*;
import ar.com.scienza.entity.*;
import ar.com.scienza.enumerator.*;
import ar.com.scienza.mailsender.MailSender;
import ar.com.scienza.remote.json.dto.AffiliateDataEmailRequestDto;
import ar.com.scienza.repository.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.json.service.IOneSignalRemoteService;
import ar.com.scienza.service.IOrderService;
import ar.com.scienza.service.IQueryService;
import ar.com.scienza.transformer.ConsultaTRF;
import ar.com.scienza.utils.CalendarUtil;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class QueryService implements IQueryService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ConsultaMensajeRepository consultaMensajeRepository;

	@Autowired
	private ConsultaSesionRepository consultaSesionRepository;

	@Autowired
	private TipoCategoriaRepository tipoCategoriaRepository;

	@Autowired
	private BandejaConsultaPendienteRepository bandejaConsultaPendienteRepository;

	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOneSignalRemoteService oneSignalRemoteService;
	
	@Autowired
	private PubNub pubNub;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private FreeMarkerConfig freeMarkerConfig;

	@Autowired
	private EmailRepository emailRepository;

	@Value("${pubnub.publishKey}")
	String publishKey;

	@Value("${pubnub.subscribeKey}")
	String subscribeKey;

	@Value("${mail.environment}")
	String mailEnvironment;
	

	@Override
	public QueryResponseDto getQuery(Integer consultaId, String token, Long sapId, String deviceType) throws ServiceException {

		QueryResponseDto response = new QueryResponseDto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm 'hs'");
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			Consulta consulta = consultaRepository.findOneByIdAndAfiliadoId(consultaId, afiliado.getId());
			if(consulta == null) {
				throw new Exception();
			}
			
			BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
			
			response.setConsultaId(consulta.getId());
			response.setPublishKey(publishKey);
			response.setSubscribeKey(subscribeKey);
			response.setCanal(consulta.getCanal());
			response.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
			
			if(EstadoBandejaEnum.PENDIENTE.getCodigo().equals(bandeja.getEstado())) {
				if(CalendarUtil.outOfWork()) {
					response.setToolbarDescription(ConsultaMensajeEnum.FUERA_HORARIO.getTitulo());
					response.setFooterDescription(ConsultaMensajeEnum.FUERA_HORARIO.getDescripcion());
				}
				else if(administradorRepository.findAllByOperativoAndFechaDeleteIsNull(true).size() == 0) {
					response.setToolbarDescription(ConsultaMensajeEnum.NO_OPERATIVO.getTitulo());
					response.setFooterDescription(ConsultaMensajeEnum.NO_OPERATIVO.getDescripcion());
				}
				else {
					response.setToolbarDescription(ConsultaMensajeEnum.PENDIENTE.getTitulo());
					response.setFooterDescription(ConsultaMensajeEnum.PENDIENTE.getDescripcion());
				}
				response.setFinalizado(false);
			}
			else if(EstadoBandejaEnum.EN_CURSO.getCodigo().equals(bandeja.getEstado())) {
				String administrador = consulta.getAdministrador() != null ? consulta.getAdministrador().getNombre() : "-";
				response.setToolbarDescription(ConsultaMensajeEnum.EN_CURSO.getTitulo().replace("ADMIN", administrador));
				response.setFooterDescription(ConsultaMensajeEnum.EN_CURSO.getDescripcion());
				response.setFinalizado(false);
			}
			else if(EstadoBandejaEnum.APROBADO.getCodigo().equals(bandeja.getEstado())) {
				String administrador = consulta.getAdministrador() != null ? consulta.getAdministrador().getNombre() : "-";
				response.setToolbarDescription(ConsultaMensajeEnum.FINALIZADO.getTitulo().replace("ADMIN", administrador));
				response.setFooterDescription(ConsultaMensajeEnum.FINALIZADO.getDescripcion());
				response.setFinalizado(true);
			}
			
			Collections.sort(consulta.getConsultaMensajeList(), new Comparator<ConsultaMensaje>() {
                @Override
                public int compare(ConsultaMensaje message1, ConsultaMensaje message2) {
                	return message1.getFechaInsert().compareTo(message2.getFechaInsert());
                }
            });
			for(ConsultaMensaje consultaMensaje : consulta.getConsultaMensajeList())
			{
				QueryMessageResponseDto responseMessage = new QueryMessageResponseDto();
				responseMessage.setEmisor((consultaMensaje.getAfiliado() != null) ?
					RolEnum.AFILIADO.getCodigo() : RolEnum.ADMIN.getCodigo()
				);
				responseMessage.setMensaje(consultaMensaje.getMensaje());
				responseMessage.setFecha(CalendarUtil.getChatDate(consultaMensaje.getFechaInsert()));
				response.getMessageList().add(responseMessage);
				
				if(consultaMensaje.getAdministrador() != null && !consultaMensaje.getLeido()) {
					consultaMensaje.setLeido(true);
					consultaMensaje.setFechaLeido(new Date());
					consultaMensaje.updateOnRepository(consultaMensajeRepository);
				}
			}
			
			// A DEPRECAR
			response.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
			response.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
			response.setFechaConsulta(sdf.format(consulta.getFechaInsert()));
			response.setDescripcion(consulta.getConsultaMensajeList().get(0).getMensaje());
			if(bandeja != null && EstadoBandejaEnum.APROBADO.getCodigo().equals(bandeja.getEstado()))
			{
				Administrador admin = bandeja.getAdministrador();
				response.setRespondido(true);
				response.setResponsableRespuesta(admin != null ? (admin.getNombre() + " " + admin.getApellido()).trim() : "-");
				response.setFechaRespuesta(sdf2.format(bandeja.getFechaAtencion()));
			}
			else
			{
				response.setRespondido(false);
				response.setResponsableRespuesta(null);
				response.setFechaRespuesta(null);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener la consulta del paciente");
		}
		
		return response;
	}


	@Override
	public List<QueryResultResponseDto> getQueryList(String token, Long sapId, String deviceType) throws ServiceException {

		List<QueryResultResponseDto> responseList = new ArrayList<QueryResultResponseDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			List<ConsultaTRF> consultaTRFList = consultaRepository.findAllTRFByAfiliadoId(afiliado.getId());
			for(ConsultaTRF consultaTRF : consultaTRFList)
			{
				Consulta consulta = consultaRepository.findOne(consultaTRF.getId());
				Integer mensajesNoLeidos = (int) consulta.getConsultaMensajeList().stream().filter(x -> x.getAdministrador() != null && !x.getLeido()).count();
				ConsultaMensaje ultimoMensaje = consulta.getConsultaMensajeList().stream().reduce((x, y) -> x.getFechaInsert().compareTo(y.getFechaInsert()) > 0 ? x : y).orElse(null);
						
				// A DEPRECAR
				BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
				
				QueryResultResponseDto response = new QueryResultResponseDto();
				response.setConsultaId(consulta.getId());
				response.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
				response.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
				response.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
                response.setFinalizado(bandeja.getEstado().equals(EstadoBandejaEnum.APROBADO.getCodigo()) || bandeja.getEstado().equals(EstadoBandejaEnum.RECHAZADO.getCodigo()));
				response.setMensajesNoLeidos(mensajesNoLeidos);
				if (response.getFinalizado()) {
					response.setFechaUltimoMensaje(CalendarUtil.getChatDate(bandeja.getFechaAtencion()));
					response.setUltimoMensaje(ConsultaMensajeEnum.FINALIZADO.getDescripcion());
				}
				else if(ultimoMensaje != null) {
					response.setFechaUltimoMensaje(CalendarUtil.getChatDate(ultimoMensaje.getFechaInsert()));
					response.setUltimoMensaje(ultimoMensaje.getMensaje());					
				}
				
				// A DEPRECAR
				response.setFechaConsulta(sdf.format(consulta.getFechaInsert()));
				response.setRespuesta((bandeja != null && bandeja.getFechaAtencion() != null) ? "Respondida el " + sdf.format(bandeja.getFechaAtencion()) : null);
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener la lista de consultas");
		}
		
		return responseList;
	}


	@Override
	public QueryCreateResponseDto createQuery(QueryCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {
	
		QueryCreateResponseDto response = null;
		
		try 
		{			
			Sesion sesion = sesionRepository.findByToken(token);
			Afiliado afiliado = sesion.getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			TipoCategoria tipoCategoria = tipoCategoriaRepository.findOne(request.getTipoCategoriaId());
			if(tipoCategoria == null) {
				throw new Exception();
			}
			
			Consulta consulta = new Consulta();
			consulta.setAfiliado(afiliado);
			consulta.setTicket(0);
			consulta.setCanal("");
			consulta.setTipoCategoria(tipoCategoria);
			consulta.createOnRepository(consultaRepository);
			
			consulta.setTicket(1000 + consulta.getId());
			consulta.setCanal("QR" + Strings.padStart(consulta.getTicket().toString(), 8, '0'));
			consulta.updateOnRepository(consultaRepository);
			
			ConsultaMensaje consultaMensaje = new ConsultaMensaje();
			consultaMensaje.setConsulta(consulta);
			consultaMensaje.setAfiliado(afiliado);
			consultaMensaje.setMensaje(request.getDescripcion().trim());
			consultaMensaje.setLeido(false);
			consultaMensaje.createOnRepository(consultaMensajeRepository);
			
			BandejaConsultaPendiente bandeja = new BandejaConsultaPendiente();
			bandeja.setConsulta(consulta);
			bandeja.setTipoDispositivo(deviceType);
			bandeja.setEstado(EstadoBandejaEnum.PENDIENTE.getCodigo());
			bandeja.setFechaConsulta(new Date());
			bandeja.createOnRepository(bandejaConsultaPendienteRepository);
			
			this.pubNub.subscribe().channels(Arrays.asList(consulta.getCanal())).withPresence().execute();
			
			response = new QueryCreateResponseDto();
			response.setConsultaId(consulta.getId());
			sendQueryEmail(consultaMensaje);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al crear la consulta del paciente");
		}
		
		return response;
	}


	@Override
	public void deleteQuery(QueryDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Sesion sesion = sesionRepository.findByToken(token);
			Afiliado afiliado = sesion.getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			this.deleteQueryInDB(request);
		}
		catch(ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al eliminar la consulta del paciente");
		}
	}


	@Override
	public QueryBatchDeleteResponseDto deleteQueryBatch(QueryBatchDeleteRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {
	    QueryBatchDeleteResponseDto responseDto = new QueryBatchDeleteResponseDto();
	    try 
	    {
	        Sesion sesion = sesionRepository.findByToken(token);
	        Afiliado afiliado = sesion.getAfiliado().getAfiliadoActivo(sapId);
	        if (afiliado == null) {
	            throw new Exception();
	        }
	
	        ArrayList<String> errorMessages = new ArrayList<>();
	        ArrayList<String> successfulDeletions = new ArrayList<>();
	
	        for (Integer queryID : request.getQueries()) {
	            QueryDeleteRequestDto deleteRequestDto = new QueryDeleteRequestDto();
	            deleteRequestDto.setConsultaId(new Integer(queryID));
	            try 
	            {
	                this.deleteQueryInDB(deleteRequestDto);
	                successfulDeletions.add(queryID.toString()); // TODO : should return ticket instead of id.
	            } catch (ServiceException e) {
	                errorMessages.add(e.getMessage());
	            }
	        }
	
	        if (errorMessages.size() > 0) {
	            responseDto.setErrors(errorMessages);
	        }
	        if (successfulDeletions.size() > 0) {
	            responseDto.setMessage("Las siguientes consultas han sido borradas: " + String.join(",", successfulDeletions));
	        }
	        else {
	            responseDto.setMessage("No se ha borrado ninguna consulta.");
	        }
	    } 
	    catch (ServiceException e) {
	        throw e;
	    } 
	    catch (Exception e) {
	        ScienzaLogger.logError(e);
	        throw new ServiceException("Error al eliminar la consulta del paciente");
	    }
	    return responseDto;
	}


    private void deleteQueryInDB(QueryDeleteRequestDto request) throws Exception, ServiceException {
        Consulta consulta = consultaRepository.findOne(request.getConsultaId());
        if (consulta == null) {
            throw new ServiceException("La consulta " + request.getConsultaId() + " no existe.");
        }

        if (consulta.isDeleted()) {
            throw new ServiceException("La consulta " + request.getConsultaId() + " ya ha sido borrada.");
        }

        BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
        if (!EstadoBandejaEnum.APROBADO.getCodigo().equals(bandeja.getEstado())) {
            throw new ServiceException("No es posible eliminar consultas que no están finalizadas, ID: " + consulta.getId());
        }

        for (ConsultaMensaje consultaMensaje : consulta.getConsultaMensajeList())
            consultaMensaje.deleteOnRepository(consultaMensajeRepository);

        for (ConsultaSesion consultaSesion : consulta.getConsultaSesionList())
            consultaSesion.deleteOnRepository(consultaSesionRepository);

        consulta.deleteOnRepository(consultaRepository);
    }


	@Override
	public AdminQueryResultResponseDto getPendingQueryList(String token, Long sapId, String deviceType) throws ServiceException {

		AdminQueryResultResponseDto response = new AdminQueryResultResponseDto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try 
		{
			Sesion sesion = sesionRepository.findByToken(token);
			Administrador administrador = sesion.getAdministrador();
			if(administrador == null) {
				throw new Exception();
			}
			
			List<BandejaConsultaPendiente> miBandejaList = bandejaConsultaPendienteRepository.findAllByEstadoAndAdministrator(EstadoBandejaEnum.EN_CURSO.getCodigo(), administrador.getId());
			for(BandejaConsultaPendiente bandeja : miBandejaList)
			{
				Consulta consulta = bandeja.getConsulta();
				Afiliado afiliado = consulta.getAfiliado();
				
				AdminQueryResultDetailResponseDto detail = new AdminQueryResultDetailResponseDto();
				detail.setConsultaId(consulta.getId());
				detail.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
				detail.setFechaConsulta(sdf.format(bandeja.getFechaConsulta()));
				detail.setNombre(afiliado.getNombre());
				detail.setApellido(afiliado.getApellido());
				detail.setSapId(afiliado.getSapId());
				detail.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
				detail.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
				response.getMisConsultasList().add(detail);
			}
			
			List<BandejaConsultaPendiente> bandejaList = bandejaConsultaPendienteRepository.findAllByEstado(EstadoBandejaEnum.PENDIENTE.getCodigo());
			for(BandejaConsultaPendiente bandeja : bandejaList)
			{
				Consulta consulta = bandeja.getConsulta();
				Afiliado afiliado = consulta.getAfiliado();
				
				AdminQueryResultDetailResponseDto detail = new AdminQueryResultDetailResponseDto();
				detail.setConsultaId(consulta.getId());
				detail.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
				detail.setFechaConsulta(sdf.format(bandeja.getFechaConsulta()));
				detail.setNombre(afiliado.getNombre());
				detail.setApellido(afiliado.getApellido());
				detail.setSapId(afiliado.getSapId());
				detail.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
				detail.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
				response.getPendientesList().add(detail);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener consultas pendientes");
		}
		
		return response;
	}


	@Override
	public List<AdminQueryResultDetailResponseDto> getHistoryQueryList(Integer queryId, Long affiliateSapId , String firstName , String lastName , String token, Long sapId, String deviceType) throws ServiceException {

		List<AdminQueryResultDetailResponseDto> responseList = new ArrayList<AdminQueryResultDetailResponseDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try 
		{
			Sesion sesion = sesionRepository.findByToken(token);
			Administrador administrador = sesion.getAdministrador();
			if(administrador == null) {
				throw new Exception();
			}
			
			List<BandejaConsultaPendiente> bandejaList = null;
			if(queryId != null || affiliateSapId != null || firstName != null | lastName != null) {
				bandejaList = bandejaConsultaPendienteRepository.findAllConsultasHistoricas(EstadoBandejaEnum.APROBADO.getCodigo(), queryId, affiliateSapId , firstName , lastName);
			}
			else {
				bandejaList = bandejaConsultaPendienteRepository.findTop500ByEstadoAndFechaDeleteIsNullOrderByFechaConsultaDesc(EstadoBandejaEnum.APROBADO.getCodigo());
			}
			for(BandejaConsultaPendiente bandeja : bandejaList)
			{
				Consulta consulta = bandeja.getConsulta();
				Afiliado afiliado = consulta.getAfiliado();
				
				AdminQueryResultDetailResponseDto detail = new AdminQueryResultDetailResponseDto();
				detail.setConsultaId(consulta.getId());
				detail.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
				detail.setFechaConsulta(sdf.format(bandeja.getFechaConsulta()));
				detail.setNombre(afiliado.getNombre());
				detail.setApellido(afiliado.getApellido());
				detail.setSapId(afiliado.getSapId());
				detail.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
				detail.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
				responseList.add(detail);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener consultas históricas");
		}
		
		return responseList;
	}


	@Override
	public void takeQuery(AdminQueryTakeRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Sesion sesion = sesionRepository.findByToken(token);
			Administrador administrador = sesion.getAdministrador();
			if(administrador == null) {
				throw new Exception();
			}
			
			Consulta consulta = consultaRepository.findOne(request.getConsultaId());
			if(consulta == null) {
				throw new Exception();
			}
			
			BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
			bandeja.setEstado(EstadoBandejaEnum.EN_CURSO.getCodigo());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaConsultaPendienteRepository);
			
			consulta.setAdministrador(administrador);
			consulta.updateOnRepository(consultaRepository);
			
			JsonObject message = new JsonObject();
			message.addProperty("type", ConsultaMensajeEnum.EN_CURSO.getCodigo());
			message.addProperty("toolbarDescription", ConsultaMensajeEnum.EN_CURSO.getTitulo().replace("ADMIN", administrador.getNombre()));
			message.addProperty("footerDescription", ConsultaMensajeEnum.EN_CURSO.getDescripcion());
			
			this.pubNub.publish().channel(consulta.getCanal()).message(message).async(new PNCallback<PNPublishResult>() {
			    @Override
			    public void onResponse(PNPublishResult result, PNStatus status) { }
			});
			
			List<ConsultaSesion> consultaLeaveList = consulta.getConsultaSesionList().stream()
										.filter(x -> x.getSesion().getAfiliado() != null && "LEAVE".equals(x.getEstado()))
										.collect(Collectors.toList());
			
			oneSignalRemoteService.sendNotification(
				consultaLeaveList.stream()
					.map(ConsultaSesion::getSesion)
					.filter(x -> x.getPlayerId() != null && x.getFechaDelete() == null)
					.collect(Collectors.toList()),
				"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
				administrador.getNombre() + " ha tomado su caso.", 
				TipoNotificacionEnum.CONSULTA_ASIGNADA, 
				consulta.getId(), 
				consulta.getAfiliado().getSapId()
			);
		}
		catch(ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al tomar la consulta pendiente");
		}
	}


	@Override
	public AdminQueryResponseDto getAdminQuery(Integer consultaId, String token, Long sapId, String deviceType) throws ServiceException {

		AdminQueryResponseDto response = new AdminQueryResponseDto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try 
		{
			BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consultaId);
			Consulta consulta = bandeja.getConsulta();
			Afiliado afiliado = consulta.getAfiliado();
			
			response.setConsultaId(consulta.getId());
			response.setPublishKey(publishKey);
			response.setSubscribeKey(subscribeKey);
			response.setCanal(consulta.getCanal());
			response.setNombre(afiliado.getNombre());
			response.setApellido(afiliado.getApellido());
			response.setSapId(afiliado.getSapId());
			response.setTicket(Strings.padStart(consulta.getTicket().toString(), 8, '0'));
			response.setFechaConsulta(sdf.format(bandeja.getFechaConsulta()));
			response.setTipoConsulta(consulta.getTipoCategoria().getTipoConsulta().getDescripcion());
			response.setTipoCategoria(consulta.getTipoCategoria().getDescripcion());
			response.setFinalizado(EstadoBandejaEnum.APROBADO.getCodigo().equals(bandeja.getEstado()));

			List<BandejaConsultaPendiente> bandejaList = bandejaConsultaPendienteRepository.findAllByEstadoAndAfiliado(EstadoBandejaEnum.APROBADO.getCodigo(), afiliado.getId());
			for(BandejaConsultaPendiente bandejaAux : bandejaList)
			{
				Consulta consultaAux = bandejaAux.getConsulta();
				if(consulta.getTicket().compareTo(consultaAux.getTicket()) != 0)
				{
					AdminQueryHistoryResponseDto history = new AdminQueryHistoryResponseDto();
					history.setConsultaId(consultaAux.getId());
					history.setTicket(Strings.padStart(consultaAux.getTicket().toString(), 8, '0'));
					history.setTipoConsulta(consultaAux.getTipoCategoria().getTipoConsulta().getDescripcion());
					history.setQueryDate(sdf.format(consultaAux.getFechaUpdate()));
					history.setTipoCategoria(consultaAux.getTipoCategoria().getDescripcion());
					response.getConsultaList().add(history);
				}
			}
			
			/* Para mantener compatibilidad se usa un token de afiliado */
			Integer index = 0;

			List<Sesion> sesionList = sesionRepository.findSesionByAffiliate(afiliado.getId());

			if(sesionList.isEmpty()) {
				throw new ServiceException("Paciente sin sesión iniciada. Comunicarse telefónicamente.");
			}

			Sesion sesion = sesionList.get(0);
			List<OrderResultResponseDto> orderResultList = orderService.getOrderList(afiliado, sesion);

			if (orderResultList.size() >= 5 ) {

                OrderResultResponseDto orderFinal = orderResultList.get(4);
                //List<OrderResultResponseDto> orderResultListAux = orderService.getOrderList(afiliado, sesion);
				List<OrderResultResponseDto> orderResultListAux = orderService.getOrderList(orderFinal.getNumeroPedido(), sesion.getToken(), afiliado.getSapId(), sesion.getTipoDispositivo(), "2.0.0");

				for (OrderResultResponseDto orderList : orderResultListAux) {

					orderResultList.add(orderList);
				}
			}

			while (index < Math.min(10, orderResultList.size()))
			{
				OrderResultResponseDto indexResult = orderResultList.get(index);
				OrderResponseDto orderResult = orderService.getOrder(indexResult.getNumeroPedido(), afiliado, sesion);
				if(orderResult != null)
				{
					OrderDeliveryResponseDto deliveryResult = orderResult.getEntregas().stream().reduce((x, y) -> x).orElse(null);
					AdminQueryOrderResponseDto order = new AdminQueryOrderResponseDto();
					order.setNumeroPedido(orderResult.getNumeroPedido());
					order.setEstadoPedido(orderResult.getEstado());
					if(deliveryResult != null) {
						order.setNumeroEntrega(deliveryResult.getNumeroEntrega());
						order.setEstadoEntrega(deliveryResult.getEstado());
						order.setFechaEntrega(deliveryResult.getFechaEntrega());
						order.setDomicilioEntrega(deliveryResult.getDomicilio());
					}
					response.getPedidoList().add(order);
				}
				index++;
			}

			Collections.sort(consulta.getConsultaMensajeList(), new Comparator<ConsultaMensaje>() {
                @Override
                public int compare(ConsultaMensaje message1, ConsultaMensaje message2) {
                	return message1.getFechaInsert().compareTo(message2.getFechaInsert());
                }
            });
			for(ConsultaMensaje consultaMensaje : consulta.getConsultaMensajeList())
			{
				QueryMessageResponseDto responseMessage = new QueryMessageResponseDto();
				responseMessage.setEmisor((consultaMensaje.getAfiliado() != null) ?
					RolEnum.AFILIADO.getCodigo() : RolEnum.ADMIN.getCodigo()
				);
				responseMessage.setMensaje(consultaMensaje.getMensaje());
				responseMessage.setFecha(CalendarUtil.getChatDate(consultaMensaje.getFechaInsert()));
				response.getMessageList().add(responseMessage);
				
				if(consultaMensaje.getAfiliado() != null && !consultaMensaje.getLeido()) {
					consultaMensaje.setLeido(true);
					consultaMensaje.setFechaLeido(new Date());
					consultaMensaje.updateOnRepository(consultaMensajeRepository);
				}
			}
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener la consulta pendiente");
		}
		
		return response;
	}


	@Override
	public void closeQuery(AdminQueryCloseRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Sesion sesion = sesionRepository.findByToken(token);
			Administrador administrador = sesion.getAdministrador();
			if(administrador == null) {
				throw new Exception();
			}
			
			Consulta consulta = consultaRepository.findOne(request.getConsultaId());
			if(consulta == null) {
				throw new Exception();
			}
			
			BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
			bandeja.setEstado(EstadoBandejaEnum.APROBADO.getCodigo());
			bandeja.setFechaAtencion(new Date());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaConsultaPendienteRepository);

			JsonObject message = new JsonObject();
			message.addProperty("type", ConsultaMensajeEnum.FINALIZADO.getCodigo());
			message.addProperty("toolbarDescription", ConsultaMensajeEnum.FINALIZADO.getTitulo().replace("ADMIN", administrador.getNombre()));
			message.addProperty("footerDescription", ConsultaMensajeEnum.FINALIZADO.getDescripcion());
			
			this.pubNub.publish().channel(consulta.getCanal()).message(message).async(new PNCallback<PNPublishResult>() {
			    @Override
			    public void onResponse(PNPublishResult result, PNStatus status) { }
			});
			
			Thread.sleep(1000);

			this.pubNub.unsubscribe().channels(Arrays.asList(consulta.getCanal())).execute();

			finishQueryEmail(bandeja);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al cerrar consulta pendiente");
		}
	}

	private void sendQueryEmail(ConsultaMensaje query) throws IOException, MessagingException, TemplateException {

		Map<Object, Object> root = new HashMap<Object, Object>();

		AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");

		String ticketNumber = " " + query.getConsulta().getTicket();

		emailRequestDto.setCaseType("Creación de consulta");
		emailRequestDto.setRequestType(query.getConsulta().getTipoCategoria().getTipoConsulta().getDescripcion());
		emailRequestDto.setCategory(query.getConsulta().getTipoCategoria().getDescripcion());
		emailRequestDto.setName(query.getAfiliado().getApellido() + " , " + query.getAfiliado().getNombre());
		emailRequestDto.setDateCase(sdf.format(query.getFechaInsert()));
		emailRequestDto.setSapId(query.getAfiliado().getSapId().toString());
		emailRequestDto.setMessage(query.getMensaje());
		emailRequestDto.setUrlToOpen(mailEnvironment);

		root.put("titulo", emailRequestDto.getCaseType());
		root.put("solicitud",emailRequestDto.getRequestType());
		root.put("categoria", emailRequestDto.getCategory());
		root.put("nombreCompleto", emailRequestDto.getName());
		root.put("sapId", emailRequestDto.getSapId());
		root.put("fechacreacion",emailRequestDto.getDateCase());
		root.put("url",emailRequestDto.getUrlToOpen());
		root.put("mensajes", emailRequestDto.getMessage());

		Template template = freeMarkerConfig.getCfg().getTemplate("html/email-consulta-datos.html");
		StringWriter stringWriter = new StringWriter();
		template.process(root, stringWriter);

		String message = stringWriter.toString();

		Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.DATOS_SOLICITUD.getCodigo());

		mailSender.sendMail(email, email.getSubject() + ticketNumber, message, null);

	}

	private void finishQueryEmail(BandejaConsultaPendiente queryFinal) throws IOException, MessagingException, TemplateException {

		Map<Object, Object> root = new HashMap<Object, Object>();

		AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");

		String mensajeLargo= "";

		String ticketNumber = " " + queryFinal.getConsulta().getTicket();

		emailRequestDto.setCaseType("Finalización de consulta");
		emailRequestDto.setRequestType(queryFinal.getConsulta().getTipoCategoria().getTipoConsulta().getDescripcion());
		emailRequestDto.setCategory(queryFinal.getConsulta().getTipoCategoria().getDescripcion());
		emailRequestDto.setName(queryFinal.getConsulta().getAfiliado().getApellido() + " , " + queryFinal.getConsulta().getAfiliado().getNombre());
		emailRequestDto.setDateCase(sdf.format(queryFinal.getFechaInsert()));
		emailRequestDto.setSapId(queryFinal.getConsulta().getAfiliado().getSapId().toString());

		Collections.sort(queryFinal.getConsulta().getConsultaMensajeList() ,new Comparator<ConsultaMensaje>() {
					@Override
					public int compare(ConsultaMensaje message1, ConsultaMensaje message2) {
						return message1.getFechaInsert().compareTo(message2.getFechaInsert());
					}
				});

		for (ConsultaMensaje mensaje : queryFinal.getConsulta().getConsultaMensajeList()) {

			if (mensaje.getAfiliado() != null) {

				mensajeLargo = mensajeLargo.concat("<br>" + "Usuario: " + mensaje.getMensaje());
			}
			else {

				mensajeLargo = mensajeLargo.concat("<br>" + "Administrador: " + mensaje.getMensaje());
			}
		}

		emailRequestDto.setMessage(mensajeLargo);
		emailRequestDto.setUrlToOpen(mailEnvironment);

		root.put("titulo", emailRequestDto.getCaseType());
		root.put("solicitud",emailRequestDto.getRequestType());
		root.put("categoria", emailRequestDto.getCategory());
		root.put("nombreCompleto", emailRequestDto.getName());
		root.put("sapId", emailRequestDto.getSapId());
		root.put("fechacreacion",emailRequestDto.getDateCase());
		root.put("url",emailRequestDto.getUrlToOpen());
		root.put("mensajes", emailRequestDto.getMessage());

		Template template = freeMarkerConfig.getCfg().getTemplate("html/email-finalizacion-consulta.html");
		StringWriter stringWriter = new StringWriter();
		template.process(root, stringWriter);

		String message = stringWriter.toString();

		Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.DATOS_FINALIZACION.getCodigo());

		mailSender.sendMail(email, email.getSubject() + ticketNumber, message, null);

	}
}