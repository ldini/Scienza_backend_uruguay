package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PendingRegistrationsResponseDto extends BodyDto{

    @JsonProperty("inProgress")
    private List<PendingRegistrationResponseDto> altasEnCurso = new ArrayList<PendingRegistrationResponseDto>();

    @JsonProperty("pending")
    private List<PendingRegistrationResponseDto> altasPendientes = new ArrayList<PendingRegistrationResponseDto>();


    public List<PendingRegistrationResponseDto> getAltasEnCurso() {
        return altasEnCurso;
    }

    public void setAltasEnCurso(List<PendingRegistrationResponseDto> altasEnCurso) {
        this.altasEnCurso = altasEnCurso;
    }

    public List<PendingRegistrationResponseDto> getAltasPendientes() {
        return altasPendientes;
    }

    public void setAltasPendientes(List<PendingRegistrationResponseDto> altasPendientes) {
        this.altasPendientes = altasPendientes;
    }


}
