package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationDeleteRequestDto {

    @NotNull(message="idNotification {required}")
    @JsonProperty("idNotification")
    private Integer notificacionId;

    public Integer getNotificacionId() {
        return notificacionId;
    }

    public void setNotificacionId(Integer notificacionId) {
        this.notificacionId = notificacionId;
    }
}
