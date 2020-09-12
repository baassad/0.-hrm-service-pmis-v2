package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeForNotificationDTO extends MasterDTO {

    //    @ValidEntityOid(Attendance.class)
    private String empId;
    private String orgId;
    private String userId;

}
