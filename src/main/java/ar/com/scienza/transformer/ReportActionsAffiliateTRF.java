package ar.com.scienza.transformer;

import java.util.Date;

public class ReportActionsAffiliateTRF {

    private Date fechaAccion;

    private String accion;

    private String tipoDispositivo;

    private Integer sapId;

    private String seguroMedico;


    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
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
