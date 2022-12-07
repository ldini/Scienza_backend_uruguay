package ar.com.scienza.remote.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZAINDPEDIDOSUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAINDPEDIDOSUYResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;


@Component
public class OrderIndexRemoteService extends AbstractSAPRemoteService {
	
	final static Logger logger = Logger.getLogger(OrderIndexRemoteService.class);

	
	@Value("${SI_OS_SCIENZA.URL.IND_PEDIDOS}")
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
		params.add("interface=" + "SI_OS_SCIENZA_IND_PEDIDOS_UY");
		params.add("interfaceNamespace=" + "urn:scienza.com.ar:Indice_Pedidos_UY");
		
		return params;
	}
	
	
	@Cacheable(
		value = "order_index_cache",
		key = "{#request.PACIENTEID, #request.PEDIDODESDE, #request.ORDEN, #request.PEDIDOSCANTIDAD}",
		cacheManager="timeoutCacheManager"
	)
	public DTSCIENZAINDPEDIDOSUYResponse getOrders(DTSCIENZAINDPEDIDOSUY request) throws RemoteServiceException {

		return (DTSCIENZAINDPEDIDOSUYResponse) this.sendRequest(new ObjectFactory().createMTSCIENZAINDPEDIDOSUY(request));
	}
}
