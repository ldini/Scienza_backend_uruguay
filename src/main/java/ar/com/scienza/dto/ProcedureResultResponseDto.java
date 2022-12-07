package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcedureResultResponseDto extends BodyDto {

    @JsonProperty("idProcedure")
    private Integer tramiteId;

    @JsonProperty("description")
    private String comentario;

    @JsonProperty("procedureDate")
    private String fechaTramite;

    @JsonProperty("fileCount")
    private Integer cantidadArchivos;

    public Integer getTramiteId() {
        return tramiteId;
    }

    public void setTramiteId(Integer tramiteId) {
        this.tramiteId = tramiteId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(String fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public Integer getCantidadArchivos() {
        return cantidadArchivos;
    }

    public void setCantidadArchivos(Integer cantidadArchivos) {
        this.cantidadArchivos = cantidadArchivos;
    }
}
