package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

public class OneSignalContentRequestRDto {

	@SerializedName("en")
	private String message;

	
	public OneSignalContentRequestRDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
