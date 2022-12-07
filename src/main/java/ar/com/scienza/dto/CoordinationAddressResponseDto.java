package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CoordinationAddressResponseDto extends BodyDto {

	@JsonProperty("type")
    private String type;
	
    @JsonProperty("addressText")
    private String addressText;

    @JsonProperty("addressCode")
    private Integer addressCode;

    @JsonProperty("pharmacy")
    private String pharmacy;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dateList")
    private ArrayList<CoordinationDateResponseDateDto> dateList;

    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public Integer getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(Integer addressCode) {
		this.addressCode = addressCode;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<CoordinationDateResponseDateDto> getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList<CoordinationDateResponseDateDto> dateList) {
		this.dateList = dateList;
	}
}
