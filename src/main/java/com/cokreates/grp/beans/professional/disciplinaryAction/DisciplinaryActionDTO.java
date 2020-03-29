package com.cokreates.grp.beans.professional.disciplinaryAction;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisciplinaryActionDTO extends MasterDTO {

	private String type, details, currentSituation, decision, courtType, observation;
    private Date dateOfJudgement;

}
