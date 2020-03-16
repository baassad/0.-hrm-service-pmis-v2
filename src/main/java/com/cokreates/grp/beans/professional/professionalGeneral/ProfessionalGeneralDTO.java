package com.cokreates.grp.beans.professional.professionalGeneral;


import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalGeneralDTO extends MasterDTO {

    private String employmentType, isGovernmentEmployee, batch, cadre, grade, payScale, designation, rank,
            officeName, govtId, enothiId, location;
    private String cadreDate, confirmationGODate, gazettedDate, joiningDate, prlDate;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
