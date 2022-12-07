package ar.com.scienza.remote.json.service;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.remote.exception.RemoteServiceException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class AbstractRemoteService {
		
	
	protected String doGet(String endpoint, Map<String, String> headers, Object data) throws RemoteServiceException {
		return this.doGet(endpoint, headers, data, false);
	}
		
	
	protected String doGet(String endpoint, Map<String, String> headers, Object data, Boolean toArray) throws RemoteServiceException {
		
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(this.getURL() + endpoint);
		String responseBody = null;
		String queryString = data.toString();

		if (queryString != null && queryString.length() > 0)
			getMethod.setQueryString("?" + queryString);

		try 
		{
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				getMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
			
			ScienzaLogger.logJSONMessage(this.getProviderRequest(), "GET " + ((queryString != null) ? queryString : ""));
			
			int statusCode = httpClient.executeMethod(getMethod);

			if (statusCode != HttpStatus.SC_OK) {
				throw new RemoteServiceException("Error de conexión con el servidor");
			}
			
			responseBody = getMethod.getResponseBodyAsString();
			
			ScienzaLogger.logJSONMessage(this.getProviderResponse(), responseBody);
		} 
		catch (HttpException e) {
			throw new RemoteServiceException(e.getMessage());

		} 
		catch (IOException e) {
			throw new RemoteServiceException(e.getMessage());
		} 
		finally {
			getMethod.releaseConnection();
		}

		return responseBody;
	}
	

	protected String doPost(String endpoint, Map<String, String> headers, Object data) throws RemoteServiceException {

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(this.getURL() + endpoint);
		String requestBody = null;
		String responseBody = null;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		try 
		{
			requestBody = gson.toJson(data);
			postMethod.setRequestEntity(new StringRequestEntity(requestBody, "application/json", "UTF-8"));
			
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				postMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
			
			ScienzaLogger.logJSONMessage(this.getProviderRequest(), requestBody);

			int statusCode = httpClient.executeMethod(postMethod);

			if (statusCode != HttpStatus.SC_OK) {
				throw new RemoteServiceException("Error de conexión con el servidor");
			}

			responseBody = postMethod.getResponseBodyAsString();

			ScienzaLogger.logJSONMessage(this.getProviderResponse(), responseBody);
		} 
		catch (HttpException e) {
			throw new RemoteServiceException(e.getMessage());
		} 
		catch (IOException e) {
			throw new RemoteServiceException(e.getMessage());
		} 
		finally {
			postMethod.releaseConnection();
		}
		
		return responseBody;
	}


	/**
	 * Obtiene el proveedor
	 * @return
	 */
	protected abstract String getProviderRequest();
	protected abstract String getProviderResponse();
	

	/**
	 * Cada servicio debe implementar su URL
	 * @return
	 */
	protected abstract String getURL();
}
