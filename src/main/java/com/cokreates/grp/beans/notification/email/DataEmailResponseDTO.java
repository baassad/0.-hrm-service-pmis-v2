package com.cokreates.grp.beans.notification.email;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataEmailResponseDTO extends MasterDTO {
        private String oid;
        private String createdBy;
        private String updatedBy;
        private String rowStatus;

        private String tag;
        private String token;
        private String subject;
        private String requestOid;
        private String message;
        private String status;
        private Timestamp timeStamp;
        private String hasAttachment;
        private Timestamp scheduledTime;

        private List<EmailRecipientDTO> sentInformation;
}
