package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NotificationMessageSentDto extends BodyDto {

    @JsonProperty("affiliateNamesList")
    private List<NotificationMessageSentAffiliateDto> affiliateNamesList;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    private String message;


    public List<NotificationMessageSentAffiliateDto> getAffiliateNamesList() {
        return affiliateNamesList;
    }

    public void setAffiliateNamesList(List<NotificationMessageSentAffiliateDto> affiliateNamesList) {
        this.affiliateNamesList = affiliateNamesList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
