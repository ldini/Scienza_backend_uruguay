package ar.com.scienza.remote.ws.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.ObjectFactory;

import com.google.common.base.Joiner;


public abstract class AbstractRemoteService {

	
	final static Logger logger = Logger.getLogger(AbstractRemoteService.class);
	

	/**
	 * Envia el mensaje request XML al servidor externo
	 * @param <T>
	 * @param data
	 * @return
	 * @throws RemoteServiceException
	 */
	@SuppressWarnings("deprecation")
	protected <T> Object sendRequest(JAXBElement<T> request) throws RemoteServiceException {

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(this.getURL());
		
		String requestMessage = null;
		String responseMessage = null;
	
		Map<String, String> headers = this.getHeaderData();
		List<String> params = this.getParameterData();

		try 
		{
			/* Time OUT */
			httpClient.setConnectionTimeout(30 * 1000);
			
			/* HEADER */
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				postMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
			
			/* PARAMETERS */			
			String queryString = (params != null && params.size() > 0) ? Joiner.on("&").join(params) : null;
			if (queryString != null)
				postMethod.setQueryString("?" + queryString);
			
			/* REQUEST */
			requestMessage = this.marshalRequest(request);		
			postMethod.setRequestEntity(new StringRequestEntity(requestMessage, "application/xml", "UTF-8"));
			ScienzaLogger.logSOAPMessage(this.getProviderRequest(), requestMessage);
						
			/* SEND MESSAGE */
			int statusCode = httpClient.executeMethod(postMethod);
			
			/* RECEIVE MESSAGE */
			responseMessage = postMethod.getResponseBodyAsString();
			ScienzaLogger.logSOAPMessage(this.getProviderResponse(), responseMessage);

			if (statusCode != HttpStatus.SC_OK) {
				throw new RemoteServiceException("Error de conexión con el servidor");
			}

			return JAXBIntrospector.getValue(this.unmarshalResponse(responseMessage));
		} 
		catch (Exception e) {
			throw new RemoteServiceException(e.getMessage());
		}
		finally {
			postMethod.releaseConnection();
		}
	}
	


	/**
	 * Envia el mensaje request XML al servidor externo
	 * @param <T>
	 * @param data
	 * @return
	 * @throws RemoteServiceException
	 */
	protected <T> Object sendRequestObject(T request) throws RemoteServiceException {

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(this.getURL());
		
		String requestMessage = null;
		String responseMessage = null;
	
		Map<String, String> headers = this.getHeaderData();
		List<String> params = this.getParameterData();

		try 
		{			
			/* HEADER */
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				postMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
			
			/* PARAMETERS */			
			String queryString = (params != null && params.size() > 0) ? Joiner.on("&").join(params) : null;
			if (queryString != null)
				postMethod.setQueryString("?" + queryString);
			
			/* REQUEST */
			requestMessage = this.marshalRequestObject(request);		
			postMethod.setRequestEntity(new StringRequestEntity(requestMessage, "application/xml", "UTF-8"));
			ScienzaLogger.logSOAPMessage(this.getProviderRequest(), requestMessage);
						
			/* SEND MESSAGE */
			int statusCode = httpClient.executeMethod(postMethod);
			
			/* RECEIVE MESSAGE */
			responseMessage = postMethod.getResponseBodyAsString();
			ScienzaLogger.logSOAPMessage(this.getProviderResponse(), responseMessage);

			if (statusCode != HttpStatus.SC_OK) {
				throw new RemoteServiceException("Error de conexión con el servidor");
			}

			return JAXBIntrospector.getValue(this.unmarshalResponse(responseMessage));
		} 
		catch (Exception e) {
			throw new RemoteServiceException(e.getMessage());
		}
		finally {
			postMethod.releaseConnection();
		}
	}
	
	
	/**
	 * Obtiene el mensaje SOAP Request
	 * @param <T>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private <T> String marshalRequest(JAXBElement<T> request) throws Exception {
	
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(), ObjectFactory.class.getClassLoader());
		Marshaller jaxbMarshaller = (Marshaller) jaxbContext.createMarshaller();
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		jaxbMarshaller.marshal(request, document);
		
		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
		soapMessage.getSOAPBody().addDocument(document);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		soapMessage.writeTo(outputStream);
		
		return new String(outputStream.toByteArray());
	}

	
	/**
	 * Obtiene el mensaje SOAP Request
	 * @param <T>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private <T> String marshalRequestObject(T request) throws Exception {
	
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(), ObjectFactory.class.getClassLoader());
		Marshaller jaxbMarshaller = (Marshaller) jaxbContext.createMarshaller();
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		jaxbMarshaller.marshal(request, document);
		
		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
		soapMessage.getSOAPBody().addDocument(document);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		soapMessage.writeTo(outputStream);
		
		return new String(outputStream.toByteArray());
	}
	
	
	/**
	 * Obtiene el objeto procesado SOAP Response
	 * @param response
	 * @return
	 */
	private Object unmarshalResponse(String response) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(), ObjectFactory.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(response.getBytes()));
		
		return unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
	}


	/**
	 * Obtiene el proveedor
	 * @return
	 */
	protected abstract String getProviderRequest();
	protected abstract String getProviderResponse();
	

	/**
	 * Obtiene el host de conexion
	 * @return
	 */
	protected abstract String getURL();
	
	
	/**
	 * Obtiene los datos del header del servicio
	 * @return
	 */
	protected abstract Map<String, String> getHeaderData();

	
	/**
	 * Obtiene los datos de queryString
	 * @return
	 */
	protected abstract List<String> getParameterData();

}
