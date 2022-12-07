package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONSULTATRAZASUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZACONSULTATRAZASUYResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;


@Component
public class TraceDetailRemoteService extends AbstractSAPRemoteService {

	final static Logger logger = Logger.getLogger(TraceDetailRemoteService.class);

	
	@Value("${SI_OS_SCIENZA.URL.TRAZA}")
	String URL;

	
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
		params.add("interface=" + "SI_OS_SCIENZA_CONSULTA_TRAZAS_UY");
		params.add("interfaceNamespace=" + "urn:scienza.com.ar:Consulta_Trazas_UY");
		
		return params;
	}
	
	
	public DTSCIENZACONSULTATRAZASUYResponse getTraceDetail(DTSCIENZACONSULTATRAZASUY request) throws RemoteServiceException {

		return (DTSCIENZACONSULTATRAZASUYResponse) this.sendRequest(new ObjectFactory().createMTSCIENZACONSULTATRAZASUY(request));
	}
}
