package ar.com.scienza.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="bandeja_perfil_pendiente")
public class BandejaPerfilPendiente extends ScienzaEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", nullable = true, referencedColumnName = "id")
	private Afiliado afiliado;

	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;

	@Column(name="apodo", nullable = true, length = 10)
	private String apodo;

	@Column(name="sexo", nullable = false, length = 1)
	private String sexo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento", nullable = true)
    private Date fechaNacimiento;

	@Column(name="provincia", nullable = true, length = 100)
	private String provincia;

	@Column(name="localidad", nullable = true, length = 100)
	private String localidad;

	@Column(name="calle", nullable = true, length = 100)
	private String calle;

	@Column(name="calle_numero", nullable = true)
	private Integer calleNumero;

	@Column(name="piso", nullable = true, length = 10)
	private String piso;

	@Column(name="departamento", nullable = true, length = 10)
	private String departamento;

	@Column(name="codigo_postal", nullable = true, length = 10)
	private String codigoPostal;
	
	@Column(name = "latitud", nullable = true, length = 10)
	private String latitud;

	@Column(name = "longitud", nullable = true, length = 10)
	private String longitud;

	@Column(name="telefono_particular", nullable = true, length = 20)
	private String telefonoParticular;

	@Column(name="telefono_laboral", nullable = true, length = 20)
	private String telefonoLaboral;
	
	@Column(name="telefono_celular", nullable = true, length = 20)
	private String telefonoCelular;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "compania_celular_id", referencedColumnName = "id", nullable = true)
	private CompaniaCelular companiaCelular;

	@Column(name="email", nullable = true, length = 50)
	private String email;

	@Column(name = "tipo_dispositivo", nullable = false, length = 3)
	private String tipoDispositivo;

	@Column(name="estado", nullable = false, length = 1)
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_edicion", nullable = false)
    private Date fechaEdicion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_atencion", nullable = true)
    private Date fechaAtencion;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", nullable = true, referencedColumnName = "id")
	private Administrador administrador;

	
	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(Integer calleNumero) {
		this.calleNumero = calleNumero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	public String getTelefonoLaboral() {
		return telefonoLaboral;
	}

	public void setTelefonoLaboral(String telefonoLaboral) {
		this.telefonoLaboral = telefonoLaboral;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public CompaniaCelular getCompaniaCelular() {
		return companiaCelular;
	}

	public void setCompaniaCelular(CompaniaCelular companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "BandejaPerfilPendiente{" +
				"afiliado=" + afiliado +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", apodo='" + apodo + '\'' +
				", sexo='" + sexo + '\'' +
				", fechaNacimiento=" + fechaNacimiento +
				", provincia='" + provincia + '\'' +
				", localidad='" + localidad + '\'' +
				", calle='" + calle + '\'' +
				", calleNumero=" + calleNumero +
				", piso='" + piso + '\'' +
				", departamento='" + departamento + '\'' +
				", codigoPostal='" + codigoPostal + '\'' +
				", latitud='" + latitud + '\'' +
				", longitud='" + longitud + '\'' +
				", telefonoParticular='" + telefonoParticular + '\'' +
				", telefonoLaboral='" + telefonoLaboral + '\'' +
				", telefonoCelular='" + telefonoCelular + '\'' +
				", companiaCelular=" + companiaCelular +
				", email='" + email + '\'' +
				", tipoDispositivo='" + tipoDispositivo + '\'' +
				", estado='" + estado + '\'' +
				", fechaEdicion=" + fechaEdicion +
				", fechaAtencion=" + fechaAtencion +
				", administrador=" + administrador +
				'}';
	}
}

