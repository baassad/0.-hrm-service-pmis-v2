package com.cokreates.grp.professional.disciplinaryAction;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisciplinaryAction extends BaseEntity {

    private String type, details, currentSituation, decision, dateOfJudgement;
}
