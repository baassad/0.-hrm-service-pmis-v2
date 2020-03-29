package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalGeneral extends BaseEntity {

	private String employmentType, isGovernmentEmployee, batch, cadre, grade, payScale, designation, rank, officeName,
			govtId, enothiId, location, presentSalary, status;
	private Date cadreDate, confirmationGODate, gazettedDate, joiningDate, prlDate;
}