package com.cokreates.grp.beans.qualification.education;

import java.sql.Date;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationDTO extends MasterDTO {

	private String degree, subject, institution, isForeignInstitution, result, achieved, scale, country;
    private Date startingDate, endingDate;

}
