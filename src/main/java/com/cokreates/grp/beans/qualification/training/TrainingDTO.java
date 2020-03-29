package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainingDTO extends MasterDTO {


    private String subjectName;
    private List<String> subjectType;
    private String institution;
    private String country;
    private String grade;
    private String position;
    private String stratedFrom;
    private String endedOn, fundingSource;

}
