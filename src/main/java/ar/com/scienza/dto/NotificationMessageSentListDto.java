package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageSentListDto extends BodyDto {

    @JsonProperty("date")
    private String date;

    @JsonProperty("title")
    private String title;

    @JsonProperty("administratorName")
    private String administratorName;

    @JsonProperty("administratorLastname")
    private String administratorLastname;

    @JsonProperty("receiversAmount")
    private Integer receiversAmount;

    @JsonProperty("messageId")
    private Integer messageId;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }


    public String getAdministratorLastname() {
        return administratorLastname;
    }

    public void setAdministratorLastname(String administratorLastname) {
        this.administratorLastname = administratorLastname;
    }

    public Integer getReceiversAmount() {
        return receiversAmount;
    }

    public void setReceiversAmount(Integer receiversAmount) {
        this.receiversAmount = receiversAmount;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }



}
