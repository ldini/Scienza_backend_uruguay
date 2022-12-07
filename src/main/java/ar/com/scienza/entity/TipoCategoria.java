package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="tipo_categoria")
@FilterDef(
	name = "tipo_categoria_filter", 
	defaultCondition = "fecha_delete IS NULL"
)
public class TipoCategoria extends ScienzaEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_consulta_id", referencedColumnName = "id", nullable = false)
	private TipoConsulta tipoConsulta;

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

	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
}
