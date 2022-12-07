package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinationDateResponseDateDto extends BodyDto {
	
	@JsonProperty("dateText")
	private String dateText;

    @JsonProperty("dateCode")
    private String date;
    
    @JsonProperty("shiftText")
    private String shiftText;

    @JsonProperty("shiftCode")
    private String shift;

    
	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShiftText() {
		return shiftText;
	}

	public void setShiftText(String shiftText) {
		this.shiftText = shiftText;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
}
