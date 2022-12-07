package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

public class GoogleMapsGeometryLocationResponseRDto {

	@SerializedName("lat")
	private String latitude;

	@SerializedName("lng")
	private String longitude;

	
	public String getLatitude() {	
		if(latitude.length() > 10)
			return latitude.substring(0, 10);
		else
			return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		if(longitude.length() >  10)
			return longitude.substring(0, 10);
		else
			return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
