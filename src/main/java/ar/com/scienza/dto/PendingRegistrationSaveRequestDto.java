package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PendingRegistrationSaveRequestDto extends BodyDto{

    @NotNull(message="idResult {required}")
    @JsonProperty("idResult")
    private Integer bandejaId;

    @NotNull(message="identification {required}")
    @Max(message="identification {max_value}", value=99999999)
    @JsonProperty("identification")
    private String cedulaIdentidad;

    @NotNull(message="sapId {required}")
    @Max(message="sapId {max_value}", value=999999999)
    @JsonProperty("sapId")
    private Long sapId;

    @NotNull(message="observation {required}")
    @JsonProperty("observation")
    private String observacion;


    public Integer getBandejaId() {
        return bandejaId;
    }

    public void setBandejaId(Integer bandejaId) {
        this.bandejaId = bandejaId;
    }

    public String getcedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setcedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public Long getSapId() {
        return sapId;
    }

    public void setSapId(Long sapId) {
        this.sapId = sapId;
    }

    public String getObservacion() { return observacion; }

    public void setObservacion(String observacion) { this.observacion = observacion; }

}
