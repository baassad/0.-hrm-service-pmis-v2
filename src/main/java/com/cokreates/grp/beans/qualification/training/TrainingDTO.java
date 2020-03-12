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
}
