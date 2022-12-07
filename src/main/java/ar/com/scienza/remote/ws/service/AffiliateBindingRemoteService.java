package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUYResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.ObjectFactory;


@Component
public class AffiliateBindingRemoteService extends AbstractSAPRemoteService {

	final static Logger logger = Logger.getLogger(AffiliateBindingRemoteService.class);
	

	@Value("${SI_OS_SCIENZA.URL.REG_USUARIO}")
    private String URL;
	
	
	@Override
	protected String getURL() {
		return URL;
	}
	

	@Override
	protected List<String> getParameterData() {
		
		List<String> params = new ArrayList<String>();
		params.add("senderParty=" + "");
		params.add("senderService=" + SENDER_SERVICE);
		params.add("receiverParty=" + "");
		params.add("receiverService=" + "");
		params.add("interface=" + "SI_OS_SCIENZA_REG_USUARIO_UY");
		params.add("interfaceNamespace=" + "urn:scienza.com.ar:Registro_Usuarios_UY");
		
		return params;
	}
	

	public DTSCIENZAREGUSUARIOUYResponse bindAffiliate(DTSCIENZAREGUSUARIOUY request) throws RemoteServiceException {
		
		return (DTSCIENZAREGUSUARIOUYResponse) this.sendRequest(new ObjectFactory().createMTSCIENZAREGUSUARIOUY(request));
	}
}