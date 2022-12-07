package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PharmacyResponseDto extends BodyDto {

    @JsonProperty("idPharmacy")
    private String idPharmacy;

    @JsonProperty("preferedPharmacy")
    private Boolean preferedPharmacy;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("texts")
    private List<String> texts;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;


    public String getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(String idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public Boolean getPreferedPharmacy() {
        return preferedPharmacy;
    }

    public void setPreferedPharmacy(Boolean preferedPharmacy) {
        this.preferedPharmacy = preferedPharmacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
