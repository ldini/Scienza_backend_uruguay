package ar.com.scienza.remote.ws.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import ar.com.scienza.config.ScienzaLogger;


public abstract class AbstractTrackerRemoteService extends AbstractRemoteService {

	@Value("${LT_SCIENZA.USER}")
	private String user;

	@Value("${LT_SCIENZA.PASSWORD}")
	private String password;
	
	
	/**
	 * LOG4F Request Proveedor
	 */
	@Override
	protected String getProviderRequest() {
		return ScienzaLogger.LTR_REQ;
	}
	
	
	/**
	 * LOG4F Respuesta Proveedor
	 */
	@Override
	protected String getProviderResponse() {
		return ScienzaLogger.LTR_RES;
	}
	
	
	/**
	 * Obtiene los datos del header del servicio
	 * @return
	 */
	@Override
	protected Map<String, String> getHeaderData() {
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "text/xml; charset=utf-8");
		
		return headers;
	}
}
