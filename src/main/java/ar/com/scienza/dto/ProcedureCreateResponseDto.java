package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcedureCreateResponseDto extends BodyDto{

    @JsonProperty("idProcedure")
    private Integer tramiteId;

    public Integer getTramiteId() {
        return tramiteId;
    }

    public void setTramiteId(Integer tramiteId) {
        this.tramiteId = tramiteId;
    }
}
