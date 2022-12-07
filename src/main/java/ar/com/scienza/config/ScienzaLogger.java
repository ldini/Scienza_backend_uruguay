package ar.com.scienza.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.scienza.entity.Transaction;

public class ScienzaLogger {

	private final static Logger logger = Logger.getLogger(ScienzaLogger.class);
	
	/* CONSTANTES */
	public final static String START    = "[METH INI]: ";
	public final static String END      = "[METH END]: ";
	public final static String ERROR    = "[SRV EXEP]: ";
	public final static String JSON_REQ = "[JSON REQ]: ";
	public final static String JSON_RES = "[JSON RES]: ";
	public final static String SAP_REQ  = "[SAP  REQ]: ";
	public final static String SAP_RES  = "[SAP  RES]: ";
	public final static String LTR_REQ  = "[LTR  REQ]: ";
	public final static String LTR_RES  = "[LTR  RES]: ";
	public final static String ONSG_REQ = "[ONSG REQ]: ";
	public final static String ONSG_RES = "[ONSG RES]: ";
	public final static String GMAP_REQ = "[GMAP REQ]: ";
	public final static String GMAP_RES = "[GMAP RES]: ";
	public final static String QRY_MSG  = "[QURY MSG]: ";
	
	
	/**
	 * Inicia el proceso
	 * @param controller
	 * @param method
	 */
	public static void startProcess(String controller, String method) {
		logger.info(START + controller + "." + method);
	}
	
	
	/**
	 * Finaliza el Proceso
	 * @param controller
	 * @param method
	 */
	public static void endProcess(String controller, String method) {
		logger.info(END + controller + "." + method);
	}
	

	/**
	 * Mensaje Custom
	 * @param controller
	 * @param method
	 */
	public static void logMessage(String type, String message) {
		logger.info(type + message);
	}
	
	
	/**
	 * Loguea un JSON Request
	 * @param object
	 */
	public static void logRequest(Object object, Transaction transaction) {
		
		if(object == null)
			return;
		
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			String json = mapper.writeValueAsString(object);
			transaction.setRequest(json.substring(0, Math.min(1024, json.length())));
			logger.info(JSON_REQ + json);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Loguea un JSON Request
	 * @param object
	 */
	public static void logResponse(Object object, Transaction transaction) {
		
		if(object == null)
			return;
		
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			String json = mapper.writeValueAsString(object);
			transaction.setResponse(json.substring(0, Math.min(2048, json.length())));
			logger.info(JSON_RES + json);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Loguea una excepcion de servicio
	 * @param e
	 */
	public static void logError(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		
		String[] stack = sw.getBuffer().toString().split("(\r\n|\n)");
		int maxLines = (stack.length > 10) ? 10 : stack.length;
		for (int n = 0; n < maxLines; n++) {
			logger.error(ERROR + stack[n].toString());
		}
		if(stack.length > 10) {
			logger.error(ERROR + "\t...");
		}
	}
	
	
	/**
	 * Loguea un mensaje de un proveedor JSON
	 * @param provider
	 * @param message
	 */
	public static void logJSONMessage(String provider, String message) {
		logger.info(provider + message.replaceAll("(\r\n|\n)", "").replaceAll(" +", " "));
	}
	
	
	/**
	 * Loguea un mensaje de un proveedor SOAP
	 * @param provider
	 * @param message
	 */
	public static void logSOAPMessage(String provider, String message) {
		logger.info(provider + message.replaceAll("(\r\n|\n)", ""));
	}
}
