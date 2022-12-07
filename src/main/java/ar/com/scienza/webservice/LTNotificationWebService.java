/*
package ar.com.scienza.webservice;

import java.util.Arrays;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.remote.exception.ServiceFaultException;
import ar.com.scienza.remote.ws.dto.DTSCIENZANOTIFENT;
import ar.com.scienza.remote.ws.dto.DTSCIENZANOTIFENTResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.service.INotificationService;
import ar.com.scienza.service.IOrderService;


@Endpoint
@Transactional(rollbackFor=ServiceFaultException.class)
public class LTNotificationWebService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;

	@Autowired
	private INotificationService notificationService;

	@Autowired
	private IOrderService orderService;
	
	
	private static final String NAMESPACE_URI_NOTIF_ENT = "http://www.scienza.com.ar/MB_BK_SCIENZA_NOTIF_ENT";
	
	
	@PayloadRoot(namespace = NAMESPACE_URI_NOTIF_ENT, localPart = "MT_SCIENZA_NOTIF_ENT")
	@ResponsePayload
	public JAXBElement<DTSCIENZANOTIFENTResponse> notificateDelivery(@RequestPayload JAXBElement<DTSCIENZANOTIFENT> root) {
		
		DTSCIENZANOTIFENTResponse response = new DTSCIENZANOTIFENTResponse();
		
		try
		{
			DTSCIENZANOTIFENT request = (DTSCIENZANOTIFENT) JAXBIntrospector.getValue(root);
			
			
			Afiliado afiliado = afiliadoRepository.findBySapId(new Long(request.getAFILIADOID()));
			if(afiliado == null)
				throw new Exception("Afiliado inexistente");
			
			if(request.getEVENTOID() == 1)
			{
				*/
/* ENTREGA EN DISTRIBUCION *//*

				String message = String.format(
					"${afiliado.nombre}, su entrega %s se encuentra en distribución." +
					"<br/><br/>" +
					"<b>Distribuidor:</b> %s <br/>" +
					"<b>Tipo de vehículo:</b> %s <br/>" +
					"<b>Patente:</b> %s <br/>" +
					"<br/>" +
					"Para conocer mayor detalle de su entrega, ingrese a la funcionalidad de \"Pedidos\"", 
					request.getNROENTREGA(),
					request.getDISTRIBUIDOR().toUpperCase(),
					request.getTIPOVEHICULO().toUpperCase(),
					request.getPATENTE().toUpperCase()
				);
				
				notificationService.createNotification(
					null,
					Arrays.asList(afiliado), 
					"Entrega en Distribución",
					String.format("${afiliado.nombre}, su entrega %s se encuentra en distribución.", request.getNROENTREGA()),
					message,
					TipoNotificacionEnum.DISTRIB_ENTREGA,
					Integer.valueOf(request.getNROPEDIDO())
				);
			}
			else if(request.getEVENTOID() == 2)
			{
				*/
/* ENTREGA EN ZONA *//*

				String message = String.format(
					"${afiliado.nombre}, su entrega %s se encuentra aproximadamente a %s de destino.",
					request.getNROENTREGA(),
					orderService.getDeliveryLocation(request.getNROENTREGA(), null, null, null).getDistancia().toLowerCase()
				);
				*/
/*
				notificationService.createNotification(
					null,
					Arrays.asList(afiliado), 
					"Entrega en Zona",
					message,
					message,
					TipoNotificacionEnum.CERCANIA_ENTREGA,
					Integer.valueOf(request.getNROPEDIDO())
				);
				*//*

			}
			
			response.setRESULTADO("OK");
		}
		catch(Exception ex) {
			ScienzaLogger.logError(ex);
			response.setRESULTADO("NO_OK");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return new ObjectFactory().createMTSCIENZANOTIFENTResponse(response);
	}
}
*/
