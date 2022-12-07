package ar.com.scienza.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ar.com.scienza.base.ScienzaEntity;
import ar.com.scienza.enumerator.TipoDocumentacionEnum;
import org.hibernate.annotations.Filter;


@Entity
@Table(name="documentacion")
public class Documentacion extends ScienzaEntity { 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
	private Afiliado afiliado;

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "documentacion",
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.REFRESH,
					CascadeType.PERSIST
			}
	)
	@Filter(name="documentacion_filter")
	private List<DocumentacionArchivo> documentacionArchivoList = new ArrayList<DocumentacionArchivo>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_documentacion_id", referencedColumnName = "id", nullable = false)
	private TipoDocumentacion tipoDocumentacion;
	
	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public List<DocumentacionArchivo> getDocumentacionArchivoArchivoList() {
		return documentacionArchivoList;
	}

	public void setDocumentacionArchivoArchivoList(List<DocumentacionArchivo> documentacionArchivoArchivoList) {
		this.documentacionArchivoList = documentacionArchivoArchivoList;
	}

	public TipoDocumentacion getTipoDocumentacion() {
		return tipoDocumentacion;
	}

	public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}
}
