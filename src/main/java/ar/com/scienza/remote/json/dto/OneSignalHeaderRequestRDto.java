package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

public class OneSignalHeaderRequestRDto {

	@SerializedName("en")
	private String title;

	
	public OneSignalHeaderRequestRDto(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
