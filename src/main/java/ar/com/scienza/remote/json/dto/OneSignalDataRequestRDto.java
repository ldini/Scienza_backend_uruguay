package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

public class OneSignalDataRequestRDto {

	@SerializedName("type")
	private String type;
	
	@SerializedName("id")
	private Integer id;
	
	@SerializedName("sapId")
	private Long sapId;

	
	public OneSignalDataRequestRDto(String type, Integer id, Long sapId) {
		super();
		this.id = id;
		this.type = type;
		this.sapId = sapId;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}
}
