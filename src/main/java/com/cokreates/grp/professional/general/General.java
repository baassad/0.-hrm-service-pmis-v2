package com.cokreates.grp.professional.general;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class General extends BaseEntity {

    private String employmentType, isGovernmentEmployee, batch, cadre, cadreDate, grade, payScale, designation, rank,
            officeName, govtId, enothiId, location, confirmationGODate, gazettedDate, joiningDate, prlDate;
    //tempData obj
}