package ar.com.scienza.remote.ws.service;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZABusquedaDispone;
import ar.com.scienza.remote.ws.dto.DTSCIENZABusquedaDisponeResponse;
import ar.com.scienza.remote.ws.dto.ObjectFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PharmacySearchRemoteService extends AbstractSAPRemoteService {

    final static Logger logger = Logger.getLogger(PharmacySearchRemoteService.class);

    @Value("${SI_OS_SCIENZA.URL.BUSQUEDA_DISPONE}")
    private String URL;

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
        params.add("interface=" + "SI_OS_SCIENZA_Busqueda_Dispone");
        params.add("interfaceNamespace=" + "urn:scienza.com.ar:Busqueda_Dispone");

        return params;
    }

    public DTSCIENZABusquedaDisponeResponse getPharmacySearch(DTSCIENZABusquedaDispone request) throws RemoteServiceException{
        return (DTSCIENZABusquedaDisponeResponse) this.sendRequest(new ObjectFactory().createMTSCIENZABusquedaDispone(request));
    }


}
