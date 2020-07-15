package com.cokreates.grp.beans.professional.professionalGeneral;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionalGeneralDTO extends MasterDTO {

	private String employmentType, isGovernmentEmployee, batch, cadre, grade, payScale, designation, rank, officeName,
			govtId, enothiId, location, presentSalary, status, rankType;
	private Date cadreDate, confirmationGODate, gazettedDate, joiningDate, prlDate;
}
