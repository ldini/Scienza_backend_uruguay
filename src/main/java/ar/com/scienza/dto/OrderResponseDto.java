package ar.com.scienza.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponseDto extends BodyDto {

	@JsonProperty("idOrder")
	private Integer pedidoId;
	
	@JsonProperty("type")
	private String tipo;
	
	@JsonProperty("status")
	private String estado;

	@JsonProperty("statusCode")
	private String codigoEstado;
	
	@JsonProperty("orderNumber")
	private Integer numeroPedido;

	@JsonProperty("orderDate")
	private String fechaPedido;

	@JsonProperty("partialCoverageAmount")
	private BigDecimal importeCoberturaParcial;

	@JsonProperty("partial")
	private Boolean parcial;

	@JsonProperty("drugs")
	private List<OrderDrugResponseDto> medicamentos = new ArrayList<OrderDrugResponseDto>();

	@JsonProperty("drugsToDeliver")
	private List<CoordinationDrugResponseDto> medicamentosDeEntrega = new ArrayList<CoordinationDrugResponseDto>();

    @JsonProperty("coordinable")
    private Boolean coordinable;

    @JsonProperty("allowUploads")
    private Boolean allowUploads;

	@JsonProperty("deliveries")
	private List<OrderDeliveryResponseDto> entregas = new ArrayList<OrderDeliveryResponseDto>();

	@JsonProperty("coordinations")
	@Deprecated
	private List<OrderDeliveryResponseDto> coordinations = new ArrayList<OrderDeliveryResponseDto>();
	
	
	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public BigDecimal getImporteCoberturaParcial() {
		return importeCoberturaParcial;
	}

	public void setImporteCoberturaParcial(BigDecimal importeCoberturaParcial) {
		this.importeCoberturaParcial = importeCoberturaParcial;
	}

	public Boolean getParcial() {
		return parcial;
	}

	public void setParcial(Boolean parcial) {
		this.parcial = parcial;
	}

	public List<OrderDrugResponseDto> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<OrderDrugResponseDto> medicamentos) {
		this.medicamentos = medicamentos;
	}


	public List<OrderDeliveryResponseDto> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<OrderDeliveryResponseDto> entregas) {
		this.entregas = entregas;
	}

    public List<CoordinationDrugResponseDto> getMedicamentosDeEntrega() {
        return medicamentosDeEntrega;
    }

    public void setMedicamentosDeEntrega(List<CoordinationDrugResponseDto> medicamentosDeEntrega) {
        this.medicamentosDeEntrega = medicamentosDeEntrega;
    }

    public Boolean getCoordinable() {
        return coordinable;
    }

    public void setCoordinable(Boolean coordinable) {
        this.coordinable = coordinable;
    }

    public Boolean getAllowUploads() {
        return allowUploads;
    }

    public void setAllowUploads(Boolean allowUploads) {
        this.allowUploads = allowUploads;
    }

	public List<OrderDeliveryResponseDto> getCoordinations() {
		return coordinations;
	}

	public void setCoordinations(List<OrderDeliveryResponseDto> coordinations) {
		this.coordinations = coordinations;
	}
}
