package ar.com.scienza.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.BandejaConsultaPendiente;
import ar.com.scienza.entity.Consulta;
import ar.com.scienza.entity.ConsultaMensaje;
import ar.com.scienza.entity.ConsultaSesion;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.ConsultaMensajeEnum;
import ar.com.scienza.enumerator.EstadoBandejaEnum;
import ar.com.scienza.enumerator.TipoDispositivoEnum;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.json.service.IOneSignalRemoteService;
import ar.com.scienza.repository.BandejaConsultaPendienteRepository;
import ar.com.scienza.repository.ConsultaMensajeRepository;
import ar.com.scienza.repository.ConsultaRepository;
import ar.com.scienza.repository.ConsultaSesionRepository;
import ar.com.scienza.repository.SesionRepository;

@Configuration
@Transactional(rollbackFor=ServiceException.class)
public class PubNubConfig {

	@Value("${pubnub.publishKey}")
	String publishKey;

	@Value("${pubnub.subscribeKey}")
	String subscribeKey;
	
	@Value("${pubnub.uuid}")
	String uuid;

	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private ConsultaMensajeRepository consultaMensajeRepository;

	@Autowired
	private ConsultaSesionRepository consultaSesionRepository;

	@Autowired
	private BandejaConsultaPendienteRepository bandejaConsultaPendienteRepository;

	@Autowired
	private IOneSignalRemoteService oneSignalRemoteService;
	
	
	@Bean
	public PubNub pubNub() throws ServiceException {
		
		PNConfiguration pnConfiguration = new PNConfiguration();
		pnConfiguration.setPublishKey(publishKey);
	    pnConfiguration.setSubscribeKey(subscribeKey);
	    pnConfiguration.setUuid(uuid);
	     
	    PubNub pubnub = new PubNub(pnConfiguration);
	 
	    pubnub.addListener(new SubscribeCallback() {

	    	@Override
	        public void status(PubNub pubnub, PNStatus status) {
	            if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
	            }
	            else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
	            }
	            else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
	            }
	            else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
	            }
	        }
	 
	    	
	        @Override
	        public void message(PubNub pubnub, PNMessageResult message) {
	        	
	        	String channel = message.getChannel();
	        	String token = message.getPublisher();
	        	
	        	try
	        	{
		        	JsonObject json = message.getMessage().getAsJsonObject();
		        	String type = (json.get("type") != null) ? json.get("type").getAsString() : null;
		        	Long sapId = (json.get("sapId") != null) ? json.get("sapId").getAsLong() : null;
		        	String description = (json.get("description") != null) ? json.get("description").getAsString() : null;
		        	
		        	if(uuid.equals(token)) {
		        		String toolbar = (json.get("toolbarDescription") != null) ? json.get("toolbarDescription").getAsString() : "";
		        		String footer = (json.get("footerDescription") != null) ? json.get("footerDescription").getAsString() : "";
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Channel: " + channel + ", Token: " + token + ", Type: " + type + ", Toolbar: " + toolbar + ", Footer: " + footer);
		        		return;
		        	}
		        	
		        	ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Channel: " + channel + ", Token: " + token + ", Mensaje: " + Strings.nullToEmpty(description));
		        	
		        	Consulta consulta = consultaRepository.findByCanal(channel);
		        	Sesion sesion = sesionRepository.findByToken(token);
		        	
		        	if(consulta == null) {
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Consulta Desconocida.");
		        	}
		        	else if(sesion == null) {
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Presencia Desconocida. Se aborta la comunicación.");
		        		throw new Exception();
		        	}
		        	else if(type == null || !"MESSAGE".equals(type) || sapId == null || description == null) {
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Información Incompleta. Se aborta la comunicación.");
		        		throw new Exception();
		        	}
		        	/* ADMINISTRADOR */
		        	else if("-1".equals(sapId.toString()))
		        	{
		        		Administrador administrador = sesion.getAdministrador();
		    			if(administrador == null) {
		    				ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Administrador Desconocido. Se aborta la comunicación.");
		    				throw new Exception();
		    			}
		    			else 
		    			{
		    				List<ConsultaSesion> consultaAfiliaList = consultaSesionRepository.findAllByConsultaCanalAndSesionAfiliadoIsNotNull(channel);
		    				List<ConsultaSesion> consultaJoinList = consultaAfiliaList.stream().filter(x -> "JOIN".equals(x.getEstado())).collect(Collectors.toList());
		    				List<ConsultaSesion> consultaLeaveList = consultaAfiliaList.stream().filter(x -> "LEAVE".equals(x.getEstado())).collect(Collectors.toList());
		    				
		    				/* Filtro WEB */
		    				String tipoWEB = TipoDispositivoEnum.WEB.getCodigo();
		    				consultaAfiliaList = consultaAfiliaList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				consultaJoinList = consultaJoinList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				consultaLeaveList = consultaLeaveList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				
		    				ConsultaMensaje consultaMensaje = new ConsultaMensaje();
		    				consultaMensaje.setConsulta(consulta);
		    				consultaMensaje.setAdministrador(administrador);
		    				consultaMensaje.setMensaje(description);
		    				consultaMensaje.setLeido(consultaJoinList.size() > 0);
		    				consultaMensaje.setFechaLeido((consultaJoinList.size() > 0) ? new Date() : null);
		    				consultaMensaje.createOnRepository(consultaMensajeRepository);
		    				
		    				if(consultaAfiliaList.size() > 0) {
		    					oneSignalRemoteService.sendNotification(
			    					consultaLeaveList.stream()
			    						.map(ConsultaSesion::getSesion)
			    						.filter(x -> x.getPlayerId() != null && x.getFechaDelete() == null)
			    						.collect(Collectors.toList()),
			    					"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
			    					description, 
			    					TipoNotificacionEnum.RTA_CONSULTA_ADMIN, 
			    					consulta.getId(), 
			    					consulta.getAfiliado().getSapId()
			    				);
		    				}
		    				else if(consulta.getAfiliado() != null) {
	    						oneSignalRemoteService.sendNotification(
	    							sesionRepository.findSesionByAffiliateWithPlayerId(consulta.getAfiliado().getId()).stream()
	    							.collect(Collectors.toList()),
			    					"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
			    					description, 
			    					TipoNotificacionEnum.RTA_CONSULTA_ADMIN, 
			    					consulta.getId(), 
			    					consulta.getAfiliado().getSapId()
			    				);
	    					}
		    			}
		        	}
		        	/* AFILIADO */
		        	else
		        	{
		        		Afiliado afiliado = sesion.getAfiliado();
		    			if(afiliado == null) {
		    				ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Afiliado Desconocido. Se aborta la comunicación.");
		    				throw new Exception();
		    			}
		    			else 
		    			{
		    				List<ConsultaSesion> consultaAdminList = consultaSesionRepository.findAllByConsultaCanalAndSesionAdministradorIsNotNull(channel);
		    				List<ConsultaSesion> consultaJoinList = consultaAdminList.stream().filter(x -> "JOIN".equals(x.getEstado())).collect(Collectors.toList());
		    				List<ConsultaSesion> consultaLeaveList = consultaAdminList.stream().filter(x -> "LEAVE".equals(x.getEstado())).collect(Collectors.toList());

		    				/* Filtro WEB */
		    				String tipoWEB = TipoDispositivoEnum.WEB.getCodigo();
		    				consultaAdminList = consultaAdminList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				consultaJoinList = consultaJoinList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				consultaLeaveList = consultaLeaveList.stream().filter(x -> !x.getSesion().getTipoDispositivo().equals(tipoWEB) || x.getSesion().getPlayerId() != null).collect(Collectors.toList());
		    				
		    				ConsultaMensaje consultaMensaje = new ConsultaMensaje();
		    				consultaMensaje.setConsulta(consulta);
		    				consultaMensaje.setAfiliado(consulta.getAfiliado());
		    				consultaMensaje.setMensaje(description);
		    				consultaMensaje.setLeido(consultaJoinList.size() > 0);
		    				consultaMensaje.setFechaLeido((consultaJoinList.size() > 0) ? new Date() : null);
		    				consultaMensaje.createOnRepository(consultaMensajeRepository);

		    				if(consultaAdminList.size() > 0) {
			    				oneSignalRemoteService.sendNotification(
			    					consultaLeaveList.stream()
			    						.map(ConsultaSesion::getSesion)
			    						.filter(x -> x.getPlayerId() != null && x.getFechaDelete() == null)
			    						.collect(Collectors.toList()),
			    					"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
			    					description, 
			    					TipoNotificacionEnum.RTA_CONSULTA_AFILIADO, 
			    					consulta.getId(), 
			    					-1L
			    				);
		    				}
		    				else if(consulta.getAdministrador() != null) {
		    					oneSignalRemoteService.sendNotification(
	    							sesionRepository.findSesionByAdmin(consulta.getAdministrador().getId()).stream()
	    							.collect(Collectors.toList()),
			    					"Consulta #" + Strings.padStart(consulta.getTicket().toString(), 8, '0'), 
			    					description, 
			    					TipoNotificacionEnum.RTA_CONSULTA_AFILIADO, 
			    					consulta.getId(), 
			    					-1L
			    				);
		    				}
		    			}
		        	}
	        	}
	        	catch(Exception ex) {
	        		this.abort(channel);
	        	}
	        }
	        
	 
	        @Override
	        public void presence(PubNub pubnub, PNPresenceEventResult presence) {
	        	
	        	String status = presence.getEvent();
	        	String channel = presence.getChannel();
	        	String token = presence.getUuid();
	        	
	        	try
	        	{
		        	ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Channel: " + channel + ", Token: " + token + ", Presencia: " + presence.getEvent().toUpperCase());
		        	if(uuid.equals(token)) {
		        		return;
		        	}
		        	
		        	Consulta consulta = consultaRepository.findByCanal(channel);
		        	Sesion sesion = sesionRepository.findByToken(token);
		        	
		        	if(consulta == null) {
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Consulta Desconocida.");
		        	}
		        	else if(sesion == null) {
		        		ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Presencia Desconocida. Se aborta la comunicación.");
		        		throw new Exception();
		        	}
		        	else 
		        	{
		        		ConsultaSesion consultaSesion = consultaSesionRepository.findByConsultaCanalAndSesionToken(channel, token);
		        		if(Arrays.asList("join", "leave").contains(status) && consultaSesion == null) {
		        			consultaSesion = new ConsultaSesion();
		        			consultaSesion.setConsulta(consulta);
		        			consultaSesion.setSesion(sesion);
		        			consultaSesion.setEstado(status.toUpperCase());
		        			consultaSesion.createOnRepository(consultaSesionRepository);
		        		}
		        		else if(Arrays.asList("join", "leave").contains(status) && consultaSesion != null) {
		        			consultaSesion.setEstado(status.toUpperCase());
		        			consultaSesion.updateOnRepository(consultaSesionRepository);
		        		}
		        	}
	        	}
	        	catch(Exception ex) {
	        		this.abort(channel);
	        	}
	        }
	        
	        
	        /**
	         * Se utilizará este metodo para cortar la comunicacion debido a mensajes inseguros
	         */
	        private void abort(String channel) {
	        	
	        	Consulta consulta = consultaRepository.findByCanal(channel);
	        	BandejaConsultaPendiente bandeja = bandejaConsultaPendienteRepository.findByConsultaId(consulta.getId());
	        	String administrador = (consulta.getAdministrador() != null) ? consulta.getAdministrador().getNombre() : "-";
	        	
	        	bandeja.setEstado(EstadoBandejaEnum.APROBADO.getCodigo());
				bandeja.setFechaAtencion(new Date());
				bandeja.updateOnRepository(bandejaConsultaPendienteRepository);
				
				JsonObject message = new JsonObject();
				message.addProperty("type", ConsultaMensajeEnum.ERROR.getCodigo());
				message.addProperty("toolbarDescription", ConsultaMensajeEnum.ERROR.getTitulo().replace("ADMIN", administrador));
				message.addProperty("footerDescription", ConsultaMensajeEnum.ERROR.getDescripcion());
				
				pubnub.publish().channel(channel).message(message).async(new PNCallback<PNPublishResult>() {
				    @Override
				    public void onResponse(PNPublishResult result, PNStatus status) { }
				});
				
				pubnub.unsubscribe().channels(Arrays.asList(channel)).execute();
	        }
	    });
	 
	    /* Suscripcion de canales pendientes de resolucion */
	    List<String> channels = new ArrayList<String>();
	    List<String> status = Arrays.asList(EstadoBandejaEnum.PENDIENTE, EstadoBandejaEnum.EN_CURSO).stream().map(EstadoBandejaEnum::getCodigo).collect(Collectors.toList());
	    for(BandejaConsultaPendiente bandeja : this.bandejaConsultaPendienteRepository.findAllByEstadoInAndFechaDeleteIsNull(status)) {
	    	ScienzaLogger.logMessage(ScienzaLogger.QRY_MSG, "Conexión Canal PUBNUB: " + bandeja.getConsulta().getCanal());
	    	channels.add(bandeja.getConsulta().getCanal());
	    }
	    if(channels.size() > 0) {
	    	pubnub.subscribe().channels(channels).withPresence().execute();
	    }
	    
	    return pubnub;
	}
}
