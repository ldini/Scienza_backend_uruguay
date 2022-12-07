package ar.com.scienza.webservice;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;

public class WebServiceValidationInterceptor extends PayloadValidatingInterceptor {

	private String namespace;
	
	
	public WebServiceValidationInterceptor(String namespace) {
		this.namespace = namespace;
	}

	
    @Override
    public QName getDetailElementName() {
        return new QName(this.namespace, "error", "mb");
    }
}