package com.cokreates.grp.beans.notification.email;

import com.cokreates.grp.beans.notification.pushNotification.NotificationRecipientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailRecipientDTO extends NotificationRecipientDTO {

    private String fieldType;

}
