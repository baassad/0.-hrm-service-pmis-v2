package com.cokreates.grp.beans.notification.email;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDTO extends MasterDTO {

    //    @ValidEntityOid(Attendance.class)
    private String oid;

    private String tag;
    private String token;

    private String subject;
    private String message;
    private String scheduledTime;
    private String hasAttachment;

    private List<EmployeeForEmailDTO> listOfEmployees;
}
