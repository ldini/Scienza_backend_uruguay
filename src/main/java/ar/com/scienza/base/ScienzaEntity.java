package ar.com.scienza.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;

@MappedSuperclass
public abstract class ScienzaEntity implements Identifiable<Integer> {

	/**
	 * CLASE BASE DE DOMINIO
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_insert", nullable = false, updatable = false)
    private Date fechaInsert;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_update", nullable = true)
    private Date fechaUpdate;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_delete", nullable = true)
    private Date fechaDelete;
	
	@Column(name="version", nullable = false, updatable = true)
	private Integer version;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getFechaInsert() {
		return fechaInsert;
	}

	public void setFechaInsert(Date fechaInsert) {
		this.fechaInsert = fechaInsert;
	}

	public Date getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public Date getFechaDelete() {
		return fechaDelete;
	}

	public void setFechaDelete(Date fechaDelete) {
		this.fechaDelete = fechaDelete;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	/**
	 * Inserta la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createOnRepository(JpaRepository repository) {
		this.fechaInsert = new Date();
		this.version = 0;
		repository.save(this);
	}

	
	/**
	 * Actualiza la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateOnRepository(JpaRepository repository) {
		this.fechaUpdate = new Date();
		this.version++;
		repository.save(this);
	}

	
	/**
	 * Elimina la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteOnRepository(JpaRepository repository) {
		this.fechaDelete = new Date();
		this.version++;
		repository.save(this);
	}

	
	/**
	 * Elimina la entidad con valores por defecto
	 * @param repository
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteOnRepository(JpaRepository repository, Boolean physicalDelete) {
		this.fechaDelete = new Date();
		this.version++;
		if(physicalDelete)
			repository.delete(this);
		else
			repository.save(this);
	}

	public Boolean isDeleted(){
		return this.fechaDelete != null;
	}
}