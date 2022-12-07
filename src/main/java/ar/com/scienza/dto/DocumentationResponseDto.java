package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DocumentationResponseDto extends BodyDto{


    @JsonProperty("idDocumentation")
    private Integer documentationId;

    @JsonProperty("documentationDate")
    private String fechaTramite;

    @JsonProperty("files")
    private List<String> fileList = new ArrayList<String>();

    @JsonProperty("documentationType")
    private String tipoDocumentacion;

    public String getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(String tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }

    public Integer getDocumentationId() {
        return documentationId;
    }

    public void setDocumentationId(Integer documentationId) {
        this.documentationId = documentationId;
    }

    public String getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(String fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
