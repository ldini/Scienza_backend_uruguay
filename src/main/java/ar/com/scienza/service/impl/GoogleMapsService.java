package ar.com.scienza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.GoogleMapsAddressResponseDto;
import ar.com.scienza.dto.GoogleMapsCoordsResponseDto;
import ar.com.scienza.dto.GoogleMapsFullDataResponseDto;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.json.dto.GoogleMapsAddressComponentResponseRDto;
import ar.com.scienza.remote.json.dto.GoogleMapsResponseRDto;
import ar.com.scienza.remote.json.dto.GoogleMapsResultResponseRDto;
import ar.com.scienza.remote.json.service.IGoogleMapsRemoteService;
import ar.com.scienza.service.IGoogleMapsService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class GoogleMapsService implements IGoogleMapsService {

	@Autowired
	private IGoogleMapsRemoteService googleMapsRemoteService;


	@Override
	public GoogleMapsCoordsResponseDto getCoords(String address, String token, Long sapId, String deviceType) throws ServiceException {

		GoogleMapsCoordsResponseDto response = null;
		
		try 
		{
			GoogleMapsResponseRDto remoteResponse =  googleMapsRemoteService.findByAddress(address);
			
			if(remoteResponse.hasResults())
			{
				GoogleMapsResultResponseRDto remoteResult = remoteResponse.getResults().get(0);				
				response = new GoogleMapsCoordsResponseDto();
				response.setLatitud(remoteResult.getGeometry().getLocation().getLatitude());
				response.setLongitud(remoteResult.getGeometry().getLocation().getLongitude());
			}
		}
		catch(RemoteServiceException e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(e.getMessage());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al buscar resultados");
		}
		
		return response;
	}


	@Override
	public GoogleMapsAddressResponseDto getAddress(String latitude, String longitude, String token, Long sapId, String deviceType) throws ServiceException {

		GoogleMapsAddressResponseDto response = null;
		
		try 
		{
			GoogleMapsResponseRDto remoteResponse =  googleMapsRemoteService.findByCoords(latitude, longitude);
			
			if(remoteResponse.hasResults())
			{
				GoogleMapsResultResponseRDto remoteResult = remoteResponse.getResults().get(0);
				
				response = new GoogleMapsAddressResponseDto();

				/* Provincia */
				response.setDepartamento(remoteResult.findAddressComponentByType("administrative_area_level_1").getShortName());
				
				/* Localidad */
				GoogleMapsAddressComponentResponseRDto locality = remoteResult.findAddressComponentByType((response.getProvincia().equals("CABA")) ? "sublocality" : "locality");
				if(locality == null) {
					locality = remoteResult.findAddressComponentByType("administrative_area_level_2");
				}
				response.setLocalidad(locality.getLongName());
				
				/* Calle */
				response.setCalle(remoteResult.findAddressComponentByType("route").getShortName());
				
				/* Nro Calle */
				GoogleMapsAddressComponentResponseRDto streetNumberComponent = remoteResult.findAddressComponentByType("street_number");
				if(streetNumberComponent != null && streetNumberComponent.getLongName().indexOf("-") == -1)
					response.setCalleNumero(Integer.valueOf(streetNumberComponent.getLongName()));
				
				/* Codigo Postal */
				GoogleMapsAddressComponentResponseRDto postalCodeComponent = remoteResult.findAddressComponentByType("postal_code");
				if(postalCodeComponent != null)
					response.setCodigoPostal(postalCodeComponent.getShortName());
			}
		}
		catch(RemoteServiceException e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(e.getMessage());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al buscar resultados");
		}
		
		return response;
	}


	@Override
	public GoogleMapsFullDataResponseDto getFullData(String address, String token, Long sapId, String deviceType) throws ServiceException {

		GoogleMapsFullDataResponseDto response = null;
		
		try 
		{
			GoogleMapsResponseRDto remoteResponse =  googleMapsRemoteService.findByAddress(address);
			
			if(remoteResponse.hasResults())
			{
				GoogleMapsResultResponseRDto remoteResult = remoteResponse.getResults().get(0);
				
				response = new GoogleMapsFullDataResponseDto();
				
				/* Provincia */
				response.setDepartamento(remoteResult.findAddressComponentByType("administrative_area_level_1").getShortName());
				
				/* Localidad */
				GoogleMapsAddressComponentResponseRDto locality = remoteResult.findAddressComponentByType((response.getProvincia().equals("CABA")) ? "sublocality" : "locality");
				if(locality == null) {
					locality = remoteResult.findAddressComponentByType("administrative_area_level_2");
				}
				response.setLocalidad(locality.getLongName());
				
				/* Calle */
				response.setCalle(remoteResult.findAddressComponentByType("route").getShortName());
				
				/* Nro Calle */
				GoogleMapsAddressComponentResponseRDto streetNumberComponent = remoteResult.findAddressComponentByType("street_number");
				if(streetNumberComponent != null && streetNumberComponent.getLongName().indexOf("-") == -1)
					response.setCalleNumero(Integer.valueOf(streetNumberComponent.getLongName()));
				
				/* Codigo Postal */
				GoogleMapsAddressComponentResponseRDto postalCodeComponent = remoteResult.findAddressComponentByType("postal_code");
				if(postalCodeComponent != null)
					response.setCodigoPostal(postalCodeComponent.getShortName());
				
				response.setLatitud(remoteResult.getGeometry().getLocation().getLatitude());
				response.setLongitud(remoteResult.getGeometry().getLocation().getLongitude());
			}
		}
		catch(RemoteServiceException e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(e.getMessage());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al buscar resultados");
		}
		
		return response;
	}
}
