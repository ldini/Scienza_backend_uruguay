package ar.com.scienza.dto;

import ar.com.scienza.enumerator.MedioDePagoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinationPaymentMethodResponseDto extends BodyDto {

    @JsonProperty("paymentCode")
    private String code;

    @JsonProperty("paymentText")
    private String description;


    public CoordinationPaymentMethodResponseDto(MedioDePagoEnum method){
        code = method.getCodigo();
        description = method.getDescripcion();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
