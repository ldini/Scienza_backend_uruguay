package ar.com.scienza.remote.json.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AffiliateDataEmailRequestDto {

    private String caseType;

    private String category;

    private String name;

    private String dateCase;

    private String sapId;

    private String identification;

    private String telephone;

    private String cellphone;

    private String email;

    private  String requestType;

    private String message;

    private String urlToOpen;

    private  String institute;

    private String pdfs;

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPdfs() {
        return pdfs;
    }

    public void setPdfs(String pdfs) {
        this.pdfs = pdfs;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getUrlToOpen() {
        return urlToOpen;
    }

    public void setUrlToOpen(String urlToOpen) {
        this.urlToOpen = urlToOpen;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCase() {
        return dateCase;
    }

    public void setDateCase(String dateCase) {
        this.dateCase = dateCase;
    }

    public String getSapId() {
        return sapId;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AffiliateDataEmailRequestDto{" +
                "caseType='" + caseType + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", dateCase='" + dateCase + '\'' +
                ", sapId='" + sapId + '\'' +
                ", identification='" + identification + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", requestType='" + requestType + '\'' +
                ", message='" + message + '\'' +
                ", urlToOpen='" + urlToOpen + '\'' +
                '}';
    }
}
