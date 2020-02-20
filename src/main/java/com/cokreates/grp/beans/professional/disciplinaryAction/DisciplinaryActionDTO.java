package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisciplinaryActionDTO extends MasterDTO {

    private String type, details, currentSituation, decision;
    private Date dateOfJudgement;
}
