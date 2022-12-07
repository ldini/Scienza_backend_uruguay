package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONFIRCOORDINACION;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONFIRCOORDINACIONResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;

@Component
public class CoordinationConfirmRemoteService extends AbstractSAPRemoteService {

    final static Logger logger = Logger.getLogger(CoordinationConfirmRemoteService.class);


    @Value("${SI_OS_SCIENZA.URL.CONF_COORDINACION}")
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
		params.add("interface=" + "SI_OS_SCIENZA_CONFIR_COORDINACION");
		params.add("interfaceNamespace=" + "urn:scienza.com.ar:Confirmacion_Coordinacion");
		
		return params;
	}

    public DTSCIENZACONFIRCOORDINACIONResponse confirmCoordination(DTSCIENZACONFIRCOORDINACION request) throws RemoteServiceException {
        return (DTSCIENZACONFIRCOORDINACIONResponse) this.sendRequest(new ObjectFactory().createMTSCIENZACONFIRCOORDINACION(request));
    }
}
