package com.cokreates.grp.beans.personal.file;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileDTO extends MasterDTO {

    private String attachmentId;
    private String type;
    private Date issueDate;
    private Date expiryDate;
    
    private String fileAttachName,fileName;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
