package ar.com.scienza.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.validation.XmlValidatorFactory;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

import ar.com.scienza.remote.exception.ServiceFaultDefinitionExceptionResolver;
import ar.com.scienza.remote.exception.ServiceFaultException;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Value("${server.protocol}://${server.host}:${server.port}/")
	String namespace;
	
	
	@Bean
	public WebServiceMessageSender webServiceMessageSender(){
		return new WebServiceMessageSender();
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("ar.com.scienza.remote.ws.dto");
		return marshaller;
	}
	
	@Bean
    public SoapFaultMappingExceptionResolver exceptionResolver(){
        SoapFaultMappingExceptionResolver exceptionResolver = new ServiceFaultDefinitionExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }
	
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet , "/ws/*");
    }
    
    
    /****************************************************
     * 				DEFINICION DE SERVICIOS
     ***************************************************/
    
    @Bean(name = "MB_BK_SCIENZA_NOTIF_ENTService")
    public Wsdl11Definition defaultWsdl11Definition_NotificacionEntrega() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/MB_BK_SCIENZA_NOTIF_ENTService.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "MB_BK_SCIENZA_NOTIF_SAPService")
    public Wsdl11Definition defaultWsdl11Definition_NotificacionSAP() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/MB_BK_SCIENZA_NOTIF_SAPService.wsdl"));
        return wsdl11Definition;
    }

    
    /****************************************************
     * 				VALIDACION DE ESQUEMAS
     ***************************************************/
    
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
    	WebServiceValidationInterceptor wsValidatorInterceptor = new WebServiceValidationInterceptor(namespace);
        wsValidatorInterceptor.setValidateRequest(true);
        wsValidatorInterceptor.setValidateResponse(true);
        
        XsdSchemaCollection schemaCollection = new XsdSchemaCollection() {
            @Override
            public XsdSchema[] getXsdSchemas() {
                return null;
            }

            @Override
            public XmlValidator createValidator() {
                try {
                    XmlValidator xmlValidator = XmlValidatorFactory.createValidator(getSchemas(), "http://www.w3.org/2001/XMLSchema");

                    return xmlValidator;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        wsValidatorInterceptor.setXsdSchemaCollection(schemaCollection);
        interceptors.add(wsValidatorInterceptor);
    }
    
    public Resource[] getSchemas() {
        List<Resource> schemaResources = new ArrayList<>();
        schemaResources.add(new ClassPathResource("/xsd/MB_BK_SCIENZA_NOTIF_ENT.xsd"));
        schemaResources.add(new ClassPathResource("/xsd/MB_BK_SCIENZA_NOTIF_SAP.xsd"));
        return schemaResources.toArray(new Resource[schemaResources.size()]);
    }
}
