package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.scienza.remote.ws.dto.DTSCIENZAPEDENTResponseUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAPEDENTUY;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.ObjectFactory;


@Component
public class OrderDetailRemoteService extends AbstractSAPRemoteService {

	final static Logger logger = Logger.getLogger(OrderDetailRemoteService.class);

	
	@Value("${SI_OS_SCIENZA.URL.PED_ENT}")
    String URL;
	

	@Override
	protected String getURL() {
		return URL;
	}

	
	@Override
	protected List<String> getParameterData() {
		
		List<String> params = new ArrayList<String>();
		params.add("senderParty=" + "");
		params.add("senderService=" + SENDER_SERVICE);
		params.add("receiverParty=" + "");
		params.add("receiverService=" + "");
		params.add("interface=" + "SI_OS_SCIENZA_PED_ENT_UY");
		params.add("interfaceNamespace=" + "urn:scienza.com.ar:Pedidos_Entregas_UY");
		
		return params;
	}

	
	@Cacheable(
		value = "order_detail_cache",
		key = "{#request.PEDIDONUMERO}",
		cacheManager="timeoutCacheManager"
	)
	public DTSCIENZAPEDENTResponseUY getOrder(DTSCIENZAPEDENTUY request) throws RemoteServiceException {

		return (DTSCIENZAPEDENTResponseUY) this.sendRequest(new ObjectFactory().createMTSCIENZAPEDENTUY(request));
	}
	
	
	@CacheEvict(
		value = "order_detail_cache",
		key = "{#request.PEDIDONUMERO}",
		cacheManager="timeoutCacheManager"
	)
	public void evictOrder(DTSCIENZAPEDENTUY request) throws RemoteServiceException {
		// Nothing to do
	}
}
