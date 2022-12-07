package ar.com.scienza.remote.ws.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import ar.com.scienza.config.ScienzaLogger;
import sun.misc.BASE64Encoder;


public abstract class AbstractSAPRemoteService extends AbstractRemoteService {

	@Value("${SI_OS_SCIENZA.USER}")
	private String user;

	@Value("${SI_OS_SCIENZA.PASSWORD}")
	private String password;

	protected static String SENDER_SERVICE = "BC_APP_Mobile";
	
	
	/**
	 * LOG4F Request Proveedor
	 */
	@Override
	protected String getProviderRequest() {
		return ScienzaLogger.SAP_REQ;
	}
	
	
	/**
	 * LOG4F Respuesta Proveedor
	 */
	@Override
	protected String getProviderResponse() {
		return ScienzaLogger.SAP_RES;
	}
	
	
	/**
	 * Obtiene los datos del header del servicio
	 * @return
	 */
	@Override
	protected Map<String, String> getHeaderData() {
		
		BASE64Encoder encoder = new BASE64Encoder();
		String userpassword = user + ":" + password;
		String encoding = encoder.encode(userpassword.getBytes());
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Authorization", "Basic " + encoding);
		headers.put("Content-Type", "text/xml; charset=utf-8");
		headers.put("SOAPAction", "http://sap.com/xi/WebService/soap1.1");
		
		return headers;
	}
}
