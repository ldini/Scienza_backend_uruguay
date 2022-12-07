package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProcedureResponseDto extends BodyDto{

    @JsonProperty("idProcedure")
    private Integer tramiteId;

    @JsonProperty("procedureDate")
    private String fechaTramite;

    @JsonProperty("description")
    private String comentario;

    @JsonProperty("files")
    private List<String> fileList = new ArrayList<String>();

    public Integer getTramiteId() {
        return tramiteId;
    }

    public void setTramiteId(Integer tramiteId) {
        this.tramiteId = tramiteId;
    }

    public String getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(String fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
