package com.cokreates.grp.beans.personal.file;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.util.Date;

@Data
public class FileDTO extends MasterDTO {

    private String attachmentId;
    private String type;
    private Date issueDate;
    private Date expiryDate;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
