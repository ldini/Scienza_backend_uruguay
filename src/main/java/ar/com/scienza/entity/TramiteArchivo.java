package ar.com.scienza.entity;

import ar.com.scienza.base.ScienzaEntity;
import org.hibernate.annotations.FilterDef;
import javax.persistence.*;


@Entity
@Table(name="tramite_archivo")
@FilterDef(
        name = "tramite_filter",
        defaultCondition = "fecha_delete IS NULL"
)
public class TramiteArchivo  extends ScienzaEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tramite_id", referencedColumnName = "id", nullable = false)
    private Tramite tramite;

    @Column(name="path", length = 100, nullable = false)
    private String path;

    @Column(name="filename", length = 100, nullable = false)
    private String filename;

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
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
