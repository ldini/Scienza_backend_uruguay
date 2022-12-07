package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class DocumentationCreateRequestDto extends BodyDto{

    @NotNull(message="images {required}")
    @JsonProperty("images")
    private List<String> imagenes;

    @NotNull(message="pdfs {required}")
    @JsonProperty("pdfs")
    private List<String> pdfs;

    @JsonProperty("dateDocumentation")
    private String fechaDocumentacion;

    @NotNull(message="documentationType {required}")
    @JsonProperty("documentationType")
    private String tipoDocumentacion;

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

    public String getFechaDocumentacion() {
        return fechaDocumentacion;
    }

    public void setFechaDocumentacion(String fechaDocumentacion) {
        this.fechaDocumentacion = fechaDocumentacion;
    }

    public String getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(String tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
    }
}
