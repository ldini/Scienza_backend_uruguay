package ar.com.scienza.remote.json.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GoogleMapsResponseRDto {

	@SerializedName("results")
	private List<GoogleMapsResultResponseRDto> results = new ArrayList<GoogleMapsResultResponseRDto>();

	@SerializedName("status")
	private String status;

	
	public List<GoogleMapsResultResponseRDto> getResults() {
		return results;
	}

	public void setResults(List<GoogleMapsResultResponseRDto> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public boolean hasResults() {
		return !"ZERO_RESULTS".equals(status);
	}
}