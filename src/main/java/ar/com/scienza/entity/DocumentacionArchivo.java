package ar.com.scienza.entity;

import ar.com.scienza.base.ScienzaEntity;
import org.hibernate.annotations.FilterDef;
import javax.persistence.*;

@Entity
@Table(name="documentacion_archivo")
@FilterDef(
        name = "documentacion_filter",
        defaultCondition = "fecha_delete IS NULL"
)
public class DocumentacionArchivo  extends ScienzaEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentacion_id", referencedColumnName = "id", nullable = false)
    private Documentacion documentacion;

    @Column(name="path", length = 100, nullable = false)
    private String path;

    @Column(name="filename", length = 100, nullable = false)
    private String filename;

    public Documentacion getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion) {
        this.documentacion = documentacion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
