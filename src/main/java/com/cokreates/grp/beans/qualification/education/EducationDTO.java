package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationDTO extends MasterDTO {

    private String degree;
    private String subject;
    private String institution;
    private String isForeignInstitution;
    private String major;
    private String result;
    private String achieved;
    private String scale;
    private String startingDate;
    private String endingDate;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
