package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainingDTO extends MasterDTO {

	private String subjectName, institution, country, grade, position, fundingSource;
    private Date stratedFrom, endedOn;
    private List<String> subjectType;

}