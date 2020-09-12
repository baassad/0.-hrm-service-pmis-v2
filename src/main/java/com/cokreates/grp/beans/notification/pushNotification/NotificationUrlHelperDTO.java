package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationUrlHelperDTO extends MasterDTO {

    private String accordion ;
    private String tab ;
    private Date dateOfReceiving ;
    private String typeOid ;
    private String officeOid ;
    private String officeUnitOid ;
    private String recipientOid ;
    private String hrmWebHomeUrl ;

}
