package ar.com.scienza.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinationResponseDto extends BodyDto {

    @JsonProperty("proposalId")
    private Integer proposalId;

    @JsonProperty("payment")
    private BigDecimal payment;

    @JsonProperty("drugs")
    private ArrayList<CoordinationDrugResponseDto> drugs;

    @JsonProperty("proposalList")
    private ArrayList<CoordinationAddressResponseDto> proposalList;

    @JsonProperty("paymentMethods")
    private ArrayList<CoordinationPaymentMethodResponseDto> paymentMethodList;

    
	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public ArrayList<CoordinationDrugResponseDto> getDrugs() {
		return drugs;
	}

	public void setDrugs(ArrayList<CoordinationDrugResponseDto> drugs) {
		this.drugs = drugs;
	}

	public ArrayList<CoordinationAddressResponseDto> getProposalList() {
		return proposalList;
	}

	public void setProposalList(ArrayList<CoordinationAddressResponseDto> proposalList) {
		this.proposalList = proposalList;
	}

	public ArrayList<CoordinationPaymentMethodResponseDto> getPaymentMethodList() {
		return paymentMethodList;
	}

	public void setPaymentMethodList(ArrayList<CoordinationPaymentMethodResponseDto> paymentMethodList) {
		this.paymentMethodList = paymentMethodList;
	}
}
