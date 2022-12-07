package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

public class GoogleMapsGeometryResponseRDto {

	@SerializedName("location")
	private GoogleMapsGeometryLocationResponseRDto location;

	
	public GoogleMapsGeometryLocationResponseRDto getLocation() {
		return location;
	}

	public void setLocation(GoogleMapsGeometryLocationResponseRDto location) {
		this.location = location;
	}
}
