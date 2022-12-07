package ar.com.scienza.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.scienza.base.ScienzaEntity;
import ar.com.scienza.dto.AffiliateProfileEditRequestDto;
import ar.com.scienza.enumerator.AvatarEnum;
import ar.com.scienza.utils.CalendarUtil;


@Entity
@Table(name="afiliado")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "getUniversoAfiliados",
		procedureName = "get_universo_afiliados",
		parameters = {}
	),
	@NamedStoredProcedureQuery(
		name = "getAccionesAfiliados",
		procedureName = "get_acciones_afiliados",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "fecha_inicio"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "fecha_fin")
		}
	)
})
public class Afiliado extends ScienzaEntity {

	
	@Column(name="sap_id", nullable = false)
	private Long sapId;
	
	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;

	@Column(name="apodo", nullable = true, length = 10)
	private String apodo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seguro_medico_id", referencedColumnName = "id", nullable = false)
	private SeguroMedico seguroMedico;

	@Column(name="numero_afiliado", nullable = true , length = 50)
	private String numeroAfiliado;

	@Column(name="numero_documento", nullable = false, length = 50)
	private String cedulaIdentidad;

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

	@Column(name="verificar_perfil", columnDefinition = "BIT", length = 1, nullable = false)
	private Boolean verificarPerfil;

	@Column(name="login_android", columnDefinition = "BIT", length = 1, nullable = false)
	private Boolean loginAndroid;

	@Column(name="login_ios", columnDefinition = "BIT", length = 1, nullable = false)
	private Boolean loginIOS;

	@Column(name="login_web", columnDefinition = "BIT", length = 1, nullable = false)
	private Boolean loginWEB;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = true)
	private Usuario usuario;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "afiliado_vinculado", 
		joinColumns = { 
			@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "afiliado_vinculado_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}
	)
	private List<Afiliado> afiliadosVinculados = new ArrayList<Afiliado>();

	
	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
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

	public SeguroMedico getSeguroMedico() {
		return seguroMedico;
	}

	public void setSeguroMedico(SeguroMedico seguroMedico) {
		this.seguroMedico = seguroMedico;
	}

	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}

	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

	public String getcedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setcedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
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

	public CompaniaCelular getCompaniaCelular() {
		return companiaCelular;
	}

	public void setCompaniaCelular(CompaniaCelular companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerificarPerfil() {
		return verificarPerfil;
	}

	public void setVerificarPerfil(Boolean verificarPerfil) {
		this.verificarPerfil = verificarPerfil;
	}

	public Boolean getLoginAndroid() {
		return loginAndroid;
	}

	public void setLoginAndroid(Boolean loginAndroid) {
		this.loginAndroid = loginAndroid;
	}

	public Boolean getLoginIOS() {
		return loginIOS;
	}

	public void setLoginIOS(Boolean loginIOS) {
		this.loginIOS = loginIOS;
	}

	public Boolean getLoginWEB() {
		return loginWEB;
	}

	public void setLoginWEB(Boolean loginWEB) {
		this.loginWEB = loginWEB;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Afiliado> getAfiliadosVinculados() {
		return afiliadosVinculados;
	}

	public void setAfiliadosVinculados(List<Afiliado> afiliadosVinculados) {
		this.afiliadosVinculados = afiliadosVinculados;
	}

	
	/**
	 * Devuelve el avatar del afiliado
	 * @return
	 */
	public String getAvatar() {

		if (this.fechaNacimiento == null || "I".equals(this.sexo)){

			return AvatarEnum.AVATAR_INDIS.getCodigo();
		}

		if(this.fechaNacimiento == null) {
			return AvatarEnum.AVATAR_INDIS.getCodigo(); 
		}

		int age = CalendarUtil.getAge(this.fechaNacimiento);
		
		if("I".equals(this.sexo)) {
			return AvatarEnum.AVATAR_INDIS.getCodigo();
		}
		else if("M".equals(this.sexo)) {
			if(age < 18) {
				return AvatarEnum.AVATAR_BOY.getCodigo();
			}
			else {
				return AvatarEnum.AVATAR_MAN.getCodigo();
			}
		}
		else if("F".equals(this.sexo)) {
			if(age < 18) {
				return AvatarEnum.AVATAR_GIRL.getCodigo();
			}
			else {
				return AvatarEnum.AVATAR_WOMAN.getCodigo();
			}
		}
		else
			return "";
	}
	
	
	/**
	 * Obtiene el usuario con un determinado sapId
	 * @param sapId
	 * @return
	 */
	public Afiliado getAfiliadoActivo(Long sapId) {
		
		if(this.sapId.compareTo(sapId) == 0) {
			return this;
		}
		else for(Afiliado afiliadoVinculado : this.getAfiliadosVinculados()) {
			if(afiliadoVinculado.getSapId().compareTo(sapId) == 0)
				return afiliadoVinculado;
		}
		
		return null;
	}
	

	/**
	 * Compara el request con la información del afiliado
	 */
	public boolean equals(AffiliateProfileEditRequestDto request) {
		return this.hashCode_profile() == request.hashCode();
	}
	

	/**
	 * hashcode afiliado
	 */
	public int hashCode_profile() {
		int hashCode = 1;
		hashCode = 11 * hashCode + (nombre == null ? 0 : nombre.hashCode());
		hashCode = 11 * hashCode + (apellido == null ? 0 : apellido.hashCode());
		hashCode = 11 * hashCode + (apodo == null ? 0 : apodo.hashCode());
		hashCode = 11 * hashCode + (sexo == null ? 0 : sexo.hashCode());
		hashCode = 11 * hashCode + (fechaNacimiento == null ? 0 : new SimpleDateFormat("dd/MM/yyyy").format(fechaNacimiento).hashCode());
		hashCode = 11 * hashCode + (provincia == null ? 0 : provincia.hashCode());
		hashCode = 11 * hashCode + (localidad == null ? 0 : localidad.hashCode());
		hashCode = 11 * hashCode + (calle == null ? 0 : calle.hashCode());
		hashCode = 11 * hashCode + (calleNumero == null ? 0 : calleNumero.hashCode());
		hashCode = 11 * hashCode + (piso == null ? 0 : piso.hashCode());
		hashCode = 11 * hashCode + (departamento == null ? 0 : departamento.hashCode());
		hashCode = 11 * hashCode + (codigoPostal == null ? 0 : codigoPostal.hashCode());
		hashCode = 11 * hashCode + (telefonoParticular == null ? 0 : telefonoParticular.hashCode());
		hashCode = 11 * hashCode + (telefonoLaboral == null ? 0 : telefonoLaboral.hashCode());
		hashCode = 11 * hashCode + (telefonoCelular == null ? 0 : telefonoCelular.hashCode());
		hashCode = 11 * hashCode + (companiaCelular == null ? 0 : companiaCelular.getCodigo().hashCode());
		hashCode = 11 * hashCode + (email == null ? 0 : email.hashCode());		
		return hashCode;
	}
}
