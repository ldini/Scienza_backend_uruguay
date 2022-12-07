package ar.com.scienza.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;

import com.google.common.base.Joiner;


@RestController
public class ErrorHandlerController implements ErrorController {

    private static final String PATH_ERROR = "/error";

	@Autowired
	private Transaction transaction;
	

	@Override
    public String getErrorPath() {
        return PATH_ERROR;
    }
	
    
	/**
	 * Forward METHOD to external system GET
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@Authorize("*")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = PATH_ERROR, 
		produces = "application/json"
	)
    public ResponseDto error(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
    	// META-DATA
    	String method = request.getMethod();
    	String url = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
    	String queryString = (request.getQueryString() != null && request.getQueryString().length() > 0) ? "?" + request.getQueryString() : "";
    	    	
		// HEADER
		List<String> headerList = new ArrayList<String>();
    	Enumeration<String> headerNames = request.getHeaderNames();
    	if(headerNames != null) {
    		while(headerNames.hasMoreElements()) {
    			String headerName = headerNames.nextElement();
    			headerList.add(headerName + ": " + request.getHeader(headerName));
    		}
    	}
    	
    	// LOG REQUEST
    	this.logRequest(method, url, queryString, headerList, null);

		return ResponseDto.newError(transaction, "Error en el servicio solicitado");
    }

    
	/**
	 * Forward METHOD to external system POST
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@Authorize("*")
	@RequestMapping(value = PATH_ERROR, method = RequestMethod.POST, produces = "application/json")
    public ResponseDto error(HttpServletRequest request, HttpServletResponse response, @RequestBody String payload) throws IOException {
       
    	// META-DATA
    	String method = request.getMethod();
    	String url = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
    	String queryString = (request.getQueryString() != null && request.getQueryString().length() > 0) ? "?" + request.getQueryString() : "";
    	
		// HEADER
		List<String> headerList = new ArrayList<String>();
    	Enumeration<String> headerNames = request.getHeaderNames();
    	if(headerNames != null) {
    		while(headerNames.hasMoreElements()) {
    			String headerName = headerNames.nextElement();
    			headerList.add(headerName + ": " + request.getHeader(headerName));
    		}
    	}
		
    	// LOG REQUEST
    	this.logRequest(method, url, queryString, headerList, payload);

		return ResponseDto.newError(transaction, "Error en el servicio solicitado");
    }
    

    /**
     * Log CONSOLE request
     * 
     * @param method
     * @param url
     * @param queryString
     * @param headerList
     * @param postData
     */
    private void logRequest(String method, String url, String queryString, List<String> headerList, String payload) {
		
    	System.out.println("");
    	System.out.println("********************************************************************************");
    	System.out.println("				ERROR												");
    	System.out.println("FORWARD URL: " + url + queryString);
    	System.out.println("METHOD: " + method);
    	System.out.println("HEADER: " + Joiner.on(", ").join(headerList));
    	if(payload != null && payload.length() > 0)
    		System.out.println("BODY: " + payload);
    	System.out.println("********************************************************************************");
	}
}
