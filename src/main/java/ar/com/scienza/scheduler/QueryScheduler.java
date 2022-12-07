package ar.com.scienza.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.BandejaConsultaPendiente;
import ar.com.scienza.entity.Consulta;
import ar.com.scienza.entity.TipoCategoria;
import ar.com.scienza.entity.TipoConsulta;
import ar.com.scienza.enumerator.EstadoBandejaEnum;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.json.service.IOneSignalRemoteService;
import ar.com.scienza.repository.AdministradorRepository;
import ar.com.scienza.repository.BandejaConsultaPendienteRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.utils.CalendarUtil;


@Component
@Transactional(rollbackFor=Exception.class)
public class QueryScheduler {

	/* Logger */
	private final static Logger logger = Logger.getLogger(QueryScheduler.class);

	@Autowired
	private BandejaConsultaPendienteRepository bandejaConsultaPendienteRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private IOneSignalRemoteService oneSignalRemoteService;
	

	/**
	 * Emite notificaciones que se requieran para iniciar el chat
	 */
	@Scheduled(fixedDelay=30000)
	public void execute() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if(CalendarUtil.outOfWork())
			return;
		
		logger.info("QueryScheduler.INIT: " + sdf.format(new Date()));
		
		List<BandejaConsultaPendiente> bandejaEnCurso = bandejaConsultaPendienteRepository.findAllByEstado(EstadoBandejaEnum.EN_CURSO.getCodigo());
		List<Integer> adminIdOcupadoList = bandejaEnCurso.stream()
							.map(BandejaConsultaPendiente::getConsulta)
							.map(Consulta::getAdministrador)
							.map(Administrador::getId)
							.collect(Collectors.toList());
		
		List<Administrador> adminLibreList = administradorRepository.findAllByOperativoAndFechaDeleteIsNull(true).stream()
							.filter(x -> !adminIdOcupadoList.contains(x.getId()))
							.collect(Collectors.toList());

		if(adminLibreList.size() > 0)
		{
			List<BandejaConsultaPendiente> bandejaPendienteList = bandejaConsultaPendienteRepository.findAllByEstado(EstadoBandejaEnum.PENDIENTE.getCodigo());
			for(BandejaConsultaPendiente bandejaPendiente : bandejaPendienteList)
			{
				try 
				{
					int index = bandejaPendienteList.indexOf(bandejaPendiente);
					Consulta consulta = bandejaPendiente.getConsulta();
					TipoConsulta tipoConsulta = consulta.getTipoCategoria().getTipoConsulta();
					TipoCategoria tipoCategoria = consulta.getTipoCategoria();
					oneSignalRemoteService.sendNotification(
						sesionRepository.findSesionByAdmin(adminLibreList.get(index % adminLibreList.size()).getId()).stream()
						.collect(Collectors.toList()),
						"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
						tipoConsulta.getDescripcion() + " (" + tipoCategoria.getDescripcion() + "). Requiere de su atención", 
						TipoNotificacionEnum.CONSULTA_NUEVA, 
						consulta.getId(), 
						consulta.getAfiliado().getSapId()
					);
				} 
				catch (RemoteServiceException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		
		logger.info("QueryScheduler.END : " + sdf.format(new Date()));
	}
}
