package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainingDTO extends MasterDTO {

    private String subjectName;
    private String  subjectType;
    private String institution;
    private String country;
    private String grade;
    private String  position;
    private Date stratedFrom;
    private Date endedOn;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
