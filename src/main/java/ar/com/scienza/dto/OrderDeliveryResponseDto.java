package ar.com.scienza.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDeliveryResponseDto {

	@JsonProperty("deliveryNumber")
	private Integer numeroEntrega;
	
	@JsonProperty("deliveryAddress")
	private String domicilio;

	@JsonProperty("deliveryPharmacy")
	private String farmacia;

	@JsonProperty("deliveryDate")
	private String fechaEntrega;

	@JsonProperty("deliveryShift")
	private String turnoEntrega;
	
	@JsonProperty("status")
	private String estado;

	@JsonProperty("statusCode")
	private String codigoEstado;

    @JsonProperty("paymentMethod")
    private String medioDePago;

	@JsonProperty("payment")
	private BigDecimal importeAbonado;

	@JsonProperty("showLocation")
	private Boolean verUbicacion;
	
	@JsonProperty("drugs")
	private List<OrderDeliveryDrugResponseDto> medicamentos = new ArrayList<OrderDeliveryDrugResponseDto>();

	@JsonProperty("deliveryLocation")

	private DeliveryLocationResponseDto ubicacionEntrega;


	public Integer getNumeroEntrega() {
		return numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(String farmacia) {
		this.farmacia = farmacia;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getTurnoEntrega() {
		return turnoEntrega;
	}

	public void setTurnoEntrega(String turnoEntrega) {
		this.turnoEntrega = turnoEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public BigDecimal getImporteAbonado() {
		return importeAbonado;
	}

	public void setImporteAbonado(BigDecimal importeAbonado) {
		this.importeAbonado = importeAbonado;
	}

	public Boolean getVerUbicacion() {
		return verUbicacion;
	}

	public void setVerUbicacion(Boolean verUbicacion) {
		this.verUbicacion = verUbicacion;
	}

	public List<OrderDeliveryDrugResponseDto> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<OrderDeliveryDrugResponseDto> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public DeliveryLocationResponseDto getUbicacionEntrega() {
		return ubicacionEntrega;
	}

	public void setUbicacionEntrega(DeliveryLocationResponseDto ubicacionEntrega) {
		this.ubicacionEntrega = ubicacionEntrega;
	}
}
