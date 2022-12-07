package ar.com.scienza.entity;

import ar.com.scienza.base.ScienzaEntity;
import org.hibernate.annotations.FilterDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tipo_documentacion")
@FilterDef(
        name = "tipo_documentacion_filter",
        defaultCondition = "fecha_delete IS NULL"
)
public class TipoDocumentacion extends ScienzaEntity {

    @Column(name="codigo", nullable = false, length = 6)
    private String codigo;

    @Column(name="descripcion", nullable = false, length = 50)
    private String descripcion;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
