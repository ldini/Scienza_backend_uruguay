/*
package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.GetStatus;
import ar.com.scienza.remote.ws.dto.GetStatusResponse;


@Component
public class DeliveryLocationRemoteService extends AbstractTrackerRemoteService {

	final static Logger logger = Logger.getLogger(DeliveryLocationRemoteService.class);


	@Value("${LT_SCIENZA.URL.UBI_ENT}")
    private String URL;
	
	
	@Override
	protected String getURL() {
		return URL;
	}
	

	@Override
	protected List<String> getParameterData() {
		List<String> params = new ArrayList<String>();
		return params;
	}

	
	public GetStatusResponse locateDelivery(GetStatus request) throws RemoteServiceException {
		
		return (GetStatusResponse) this.sendRequestObject(request);
	}
}
*/
