package ar.com.scienza.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class ScienzaMDCFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try 
        {
        	HttpServletRequest httpRequest = (HttpServletRequest) request;
        	
        	String afiliado = httpRequest.getHeader("SapId");
        	String device = httpRequest.getHeader("DeviceType");
        	String token = httpRequest.getHeader("Token");
        	
            String mdcData = String.format("[AFILIADO=%-8s][DEVICE=%s][TOKEN=%-36s] ", afiliado, device.toUpperCase(), token.toUpperCase());
            MDC.put("mdcData", mdcData);
            
            chain.doFilter(request, response);
        }
        catch(Exception e) {
        	MDC.put("mdcData", ""); 
            chain.doFilter(request, response);
        }
        finally {
           MDC.clear();
        }
    }

	@Override
	public void destroy() {	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
}
