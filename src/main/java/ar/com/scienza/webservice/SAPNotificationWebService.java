package ar.com.scienza.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import ar.com.scienza.remote.ws.dto.DTSCIENZANOTIFSAP;
import ar.com.scienza.remote.ws.dto.DTSCIENZANOTIFSAPResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.service.INotificationService;


@Endpoint
@Transactional(rollbackFor=ServiceFaultException.class)
public class SAPNotificationWebService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;	

	@Autowired
	private INotificationService notificationService;
	
	
	private static final String NAMESPACE_URI_NOTIF_SAP = "http://www.scienza.com.uy/MB_BK_SCIENZA_NOTIF_SAP";
	
	
	@PayloadRoot(namespace = NAMESPACE_URI_NOTIF_SAP, localPart = "MT_SCIENZA_NOTIF_SAP")
	@ResponsePayload
	public JAXBElement<DTSCIENZANOTIFSAPResponse> receiveNotification(@RequestPayload JAXBElement<DTSCIENZANOTIFSAP> root) {
		
		DTSCIENZANOTIFSAPResponse response = new DTSCIENZANOTIFSAPResponse();
		
		try
		{
			DTSCIENZANOTIFSAP request = (DTSCIENZANOTIFSAP) JAXBIntrospector.getValue(root);
			
			Afiliado afiliado = afiliadoRepository.findBySapId(request.getPACIENTEID());
			if(afiliado == null)
				throw new Exception("Afiliado inexistente");
			
			if("MENSAJE".equals(request.getACCION()))
			{
				String message = String.format(
					this.getHTML("html/notif-sap-mensaje.html"),
					afiliado.getNombre(),
					request.getTEXTO()
				);
				notificationService.createNotification(
					null,
					Arrays.asList(afiliado), 
					request.getTITULO(),
					"${afiliado.nombre}, " + request.getTEXTONOTIFICACIONPUSH(),
					message,
					TipoNotificacionEnum.MENSAJE_ADMIN,
					null
				);
			}
			else if("DETALLE_ENTREGA".equals(request.getACCION())) {
				String message = String.format(
					this.getHTML("html/notif-sap-detalle-entrega.html"),
					afiliado.getNombre(),
					request.getTEXTO(),
					"pedido:" + request.getDATOCLAVE().trim()
				);
				notificationService.createNotification(
					null,
					Arrays.asList(afiliado), 
					request.getTITULO(),
					"${afiliado.nombre}, " + request.getTEXTONOTIFICACIONPUSH(),
					message,
					TipoNotificacionEnum.MENSAJE_ADMIN,
					null
				);
			}

			response.setESTADO("OK");
		}
		catch(Exception ex) {
			ScienzaLogger.logError(ex);
			response.setESTADO("NO_OK");
			response.setMENSAJE(ex.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return new ObjectFactory().createMTSCIENZANOTIFSAPResponse(response);
	}
	

	private String getHTML(String fileName) throws IOException {

		StringBuilder result = new StringBuilder("");
		InputStream is = new ClassPathResource(fileName).getInputStream();

		try (Scanner scanner = new Scanner(is)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		}

		return result.toString();
	}
}
