package com.cokreates.grp.beans.notification.pushNotification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationRecipientDTO extends EmployeeForNotificationDTO {

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;

    private String status;
}
