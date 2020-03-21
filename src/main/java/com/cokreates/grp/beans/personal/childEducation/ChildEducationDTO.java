package com.cokreates.grp.beans.personal.childEducation;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChildEducationDTO extends MasterDTO {

    private String institutionName;
    private String classes;
    private String subject;
    private String year;
    private String result;
    private String comment;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}