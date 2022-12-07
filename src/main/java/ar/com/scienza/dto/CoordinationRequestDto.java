package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinationRequestDto extends BodyDto {

    @JsonProperty("orderId")
    private Integer orderId;

    @JsonProperty("proposalId")
    private Integer proposalId;

    @JsonProperty("accepted")
    private Boolean accepted;

    @JsonProperty("changePharmacy")
    private Boolean changePharmacy;

    @JsonProperty("addressCode")
    private Integer addressCode;

    @JsonProperty("dateCode")
    private String dateCode;

    @JsonProperty("shiftCode")
    private String shiftCode;

    @JsonProperty("paymentCode")
    private String paymentCode;

    @JsonProperty("rejectComment")
    private String rejectComment;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getChangePharmacy() {
        return changePharmacy;
    }

    public void setChangePharmacy(Boolean changePharmacy) {
        this.changePharmacy = changePharmacy;
    }

    public Integer getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Integer addressCode) {
        this.addressCode = addressCode;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getRejectComment() {
        return rejectComment;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }
}
