package ar.com.scienza.remote.json.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.remote.exception.RemoteServiceException;
import ar.com.scienza.remote.json.dto.GoogleMapsResponseRDto;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Component
public class GoogleMapsRemoteService extends AbstractRemoteService implements IGoogleMapsRemoteService {

	@Value("${googlemaps.apikey}")
    String api_key;


	@Override
	protected String getProviderRequest() {
		return ScienzaLogger.GMAP_REQ;
	}


	@Override
	protected String getProviderResponse() {
		return ScienzaLogger.GMAP_RES;
	}
	
	
	@Override
	protected String getURL() {
		return "https://maps.googleapis.com/maps/api";
	}
	

	@Override
	public GoogleMapsResponseRDto findByAddress(String address) throws RemoteServiceException {
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		List<String> params = new ArrayList<String>();
		params.add("address=" + this.normalizeAddress(address));
		params.add("key=" + api_key);
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		
		String response = this.doGet("/geocode/json", headers, Joiner.on("&").join(params));

		return gson.fromJson(response, GoogleMapsResponseRDto.class);	
	}


	@Override
	public GoogleMapsResponseRDto findByCoords(String latitude, String longitude) throws RemoteServiceException {
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		List<String> params = new ArrayList<String>();
		params.add("latlng=" + latitude + "," + longitude);
		params.add("key=" + api_key);
		
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		
		String response = this.doGet("/geocode/json", headers, Joiner.on("&").join(params));

		return gson.fromJson(response, GoogleMapsResponseRDto.class);	
	}


	/**
	 * Normaliza la dirección en formato ASCII
	 * @param address
	 * @return
	 */
	private String normalizeAddress(String address) {
		
		String value = address.replace(" ", "+");
		
		value = value.replace("á", "a");
		value = value.replace("é", "e");
		value = value.replace("í", "i");
		value = value.replace("ó", "o");
		value = value.replace("ú", "u");

		value = value.replace("Á", "A");
		value = value.replace("É", "E");
		value = value.replace("Í", "I");
		value = value.replace("Ó", "O");
		value = value.replace("Ú", "U");
		
		value = value.replace("ü", "u");
		value = value.replace("Ü", "U");
		value = value.replace("'", "");
		value = value.replace("\"", "");
		
		return value;
	}
}
