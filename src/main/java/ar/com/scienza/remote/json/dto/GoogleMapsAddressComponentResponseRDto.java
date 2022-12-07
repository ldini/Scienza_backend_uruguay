package ar.com.scienza.remote.json.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GoogleMapsAddressComponentResponseRDto {

	@SerializedName("long_name")
	private String longName;

	@SerializedName("short_name")
	private String shortName;

	@SerializedName("types")
	private List<String> types = new ArrayList<String>();

	
	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
