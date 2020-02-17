package com.cokreates.grp.professional.general;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class General extends BaseEntity {

    private String employmentType, isGovernmentEmployee, batch, cadre, grade, payScale, designation, rank,
            officeName, govtId, enothiId, location;
    private Date cadreDate, confirmationGODate, gazettedDate, joiningDate, prlDate;
    //tempData obj
}