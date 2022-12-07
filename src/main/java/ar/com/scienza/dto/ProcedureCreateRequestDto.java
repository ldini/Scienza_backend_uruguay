package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ProcedureCreateRequestDto extends BodyDto{

    @NotEmpty(message="description {required}")
    @JsonProperty("description")
    private String descripcion;

    @JsonProperty("images")
    private List<String> imagenes;

    @JsonProperty("pdfs")
    private List<String> pdfs;

    @JsonProperty("dateProcedure")
    private String fechaTramite;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<String> pdfs) {
        this.pdfs = pdfs;
    }

    public String getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(String fechaTramite) {
        this.fechaTramite = fechaTramite;
    }
}
