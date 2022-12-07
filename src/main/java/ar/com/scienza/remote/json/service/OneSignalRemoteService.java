package ar.com.scienza.remote.json.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.TipoDispositivoEnum;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.json.dto.OneSignalContentRequestRDto;
import ar.com.scienza.remote.json.dto.OneSignalDataRequestRDto;
import ar.com.scienza.remote.json.dto.OneSignalHeaderRequestRDto;
import ar.com.scienza.remote.json.dto.OneSignalRequestRDto;
import ar.com.scienza.repository.ConsultaRepository;
import ar.com.scienza.repository.NotificacionRepository;


@Component
public class OneSignalRemoteService extends AbstractRemoteService implements IOneSignalRemoteService {
	
	@Autowired
	private NotificacionRepository notificacionRepository;

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Value("${onesignal.authorization}")
    String authorization;

	@Value("${onesignal.appId}")
    String appId;

	@Value("${server.web}")
    String serverWeb;


	@Override
	protected String getProviderRequest() {
		return ScienzaLogger.ONSG_REQ;
	}


	@Override
	protected String getProviderResponse() {
		return ScienzaLogger.ONSG_RES;
	}
	
	
	@Override
	protected String getURL() {
		return "https://onesignal.com/api/v1";
	}


	@Override
	public void sendNotification(
		List<Sesion> sesionList, 
		String title, 
		String message, 
		TipoNotificacionEnum type, 
		Integer elementId,
		Long sapId
	) throws RemoteServiceException {

		if(sesionList.size() == 0)
			return;
		
		List<Sesion> sesionMOB = new ArrayList<Sesion>();
		List<Sesion> sesionWEB = new ArrayList<Sesion>();
		
		for (Sesion sesion : sesionList) {
			if(TipoDispositivoEnum.WEB.getCodigo().equals(sesion.getTipoDispositivo())) {
				sesionWEB.add(sesion);
			}
			else {
				sesionMOB.add(sesion);
			}
		}
		if(sesionWEB.size() > 0)
		{
			OneSignalRequestRDto request = new OneSignalRequestRDto();
			request.setAppId(appId);
			request.setPlayers(sesionWEB.stream().map(Sesion::getPlayerId).collect(Collectors.toList()));
			request.setData(new OneSignalDataRequestRDto(type.getCodigo(), elementId, sapId));
			request.setHeader(new OneSignalHeaderRequestRDto(title));
			request.setIcon("ic_launcher");
			request.setContent(new OneSignalContentRequestRDto(message));
			request.setUrl(serverWeb);
			
			Map<String, String> headers = new LinkedHashMap<String, String>();
			headers.put("Authorization", "Basic " + authorization);
			headers.put("Content-Type", "application/json; charset=UTF-8");
			
			this.doPost("/notifications", headers, request);	
		}
		if(sesionMOB.size() > 0)
		{
			for(Sesion sesion : sesionList)
			{
				Afiliado afiliado = sesion.getAfiliado();
				
				Integer consultasNoLeidas = consultaRepository.countByConsultasNoLeidas(afiliado.getId()).intValue();
				Integer notificacionesNoLeidas = notificacionRepository.countByAfiliadoIdAndLeidoFalseAndFechaDeleteIsNull(afiliado.getId()).intValue();
				
				for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados()) {
					consultasNoLeidas += consultaRepository.countByConsultasNoLeidas(afiliadoVinculado.getId()).intValue();
					notificacionesNoLeidas += notificacionRepository.countByAfiliadoIdAndLeidoFalseAndFechaDeleteIsNull(afiliadoVinculado.getId()).intValue();
				}
									
				OneSignalRequestRDto request = new OneSignalRequestRDto();
				request.setAppId(appId);
				request.setPlayers(Arrays.asList(sesion).stream().map(Sesion::getPlayerId).collect(Collectors.toList()));
				request.setData(new OneSignalDataRequestRDto(type.getCodigo(), elementId, sapId));
				request.setHeader(new OneSignalHeaderRequestRDto(title));
				request.setIcon("ic_launcher");
				request.setContent(new OneSignalContentRequestRDto(message));
				request.setBadgeType("SetTo");
				request.setBadgeCount(consultasNoLeidas + notificacionesNoLeidas);
				
				Map<String, String> headers = new LinkedHashMap<String, String>();
				headers.put("Authorization", "Basic " + authorization);
				headers.put("Content-Type", "application/json; charset=UTF-8");
				
				this.doPost("/notifications", headers, request);	
			}
		}
	}
}
