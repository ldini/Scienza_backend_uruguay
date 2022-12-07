package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationResultRespDto extends BodyDto {

    @JsonProperty("idDocumentation")
    private Integer documentationId;

    @JsonProperty("documentationType")
    private String tipoDocumentacion;

    @JsonProperty("documentationDate")
    private String fechaTramite;

    @JsonProperty("fileCount")
    private Integer cantidadArchivos;

    public Integer getDocumentationId() {
        return documentationId;
    }

    public void setDocumentationId(Integer documentationId) {
        this.documentationId = documentationId;
    }

    public String getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(String tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
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
