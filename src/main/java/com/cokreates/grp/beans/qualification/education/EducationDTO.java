package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationDTO extends MasterDTO {

    private String degree;
    private String subject;
    private String institution;
    private String isForeignInstitution;
    private String  major;
    private String result;
    private String achieved;
    private String scale;
    private Date startingDate;
    private Date endingDate;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
