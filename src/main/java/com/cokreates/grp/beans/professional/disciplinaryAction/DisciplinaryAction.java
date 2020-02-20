package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisciplinaryAction extends BaseEntity {

    private String type, details, currentSituation, decision;
    private Date dateOfJudgement;
}
