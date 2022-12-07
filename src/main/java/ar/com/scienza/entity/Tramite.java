package ar.com.scienza.entity;

import ar.com.scienza.base.ScienzaEntity;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tramite")
public class Tramite extends ScienzaEntity {

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
        private Afiliado afiliado;

        @Column(name="descripcion", nullable = false, length = 500)
        private String descripcion;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "tramite",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    @Filter(name="tramite_filter")
    private List<TramiteArchivo> tramiteArchivoList = new ArrayList<TramiteArchivo>();

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TramiteArchivo> getTramiteArchivoList() {
        return tramiteArchivoList;
    }

    public void setTramiteArchivoList(List<TramiteArchivo> tramiteArchivoList) {
        this.tramiteArchivoList = tramiteArchivoList;
    }
}
