package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportActionsAffiliateResponseDto extends BodyDto {

    @JsonProperty("actionDate")
    private String fechaAccion;

    @JsonProperty("action")
    private String accion;

    @JsonProperty("deviceType")
    private String tipoDispositivo;

    @JsonProperty("sapId")
    private Integer sapId;

    @JsonProperty("healthInsurance")
    private String seguroMedico;


    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Integer getSapId() {
        return sapId;
    }

    public void setSapId(Integer sapId) {
        this.sapId = sapId;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }
}
