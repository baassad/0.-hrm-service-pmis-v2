package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDTO extends MasterDTO {

    //    @ValidEntityOid(Attendance.class)
    private String oid;

    private String tag;
    private String token;
    private String url;
    private String callbackApi;
    private String message;

    private List<EmployeeForNotificationDTO> listOfEmployees;
}
