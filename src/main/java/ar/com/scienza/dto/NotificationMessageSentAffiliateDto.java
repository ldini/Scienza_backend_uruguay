package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageSentAffiliateDto {

    private String firstName;

    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
