package ar.com.scienza.service;

import ar.com.scienza.dto.GoogleMapsAddressResponseDto;
import ar.com.scienza.dto.GoogleMapsCoordsResponseDto;
import ar.com.scienza.dto.GoogleMapsFullDataResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IGoogleMapsService {

	public GoogleMapsCoordsResponseDto getCoords(String address, String token, Long sapId, String deviceType) throws ServiceException;

	public GoogleMapsAddressResponseDto getAddress(String latitude,	String longitude, String token, Long sapId, String deviceType) throws ServiceException;

	public GoogleMapsFullDataResponseDto getFullData(String address, String token, Long sapId, String deviceType) throws ServiceException;
	
}
