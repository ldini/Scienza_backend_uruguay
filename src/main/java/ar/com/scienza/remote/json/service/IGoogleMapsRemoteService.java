package ar.com.scienza.remote.json.service;

import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.json.dto.GoogleMapsResponseRDto;


public interface IGoogleMapsRemoteService {

	public GoogleMapsResponseRDto findByAddress(String address) throws RemoteServiceException;

	public GoogleMapsResponseRDto findByCoords(String latitude, String longitude) throws RemoteServiceException;

}
