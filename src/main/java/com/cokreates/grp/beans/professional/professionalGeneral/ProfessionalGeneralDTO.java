package com.cokreates.grp.beans.professional.professionalGeneral;


import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalGeneralDTO extends MasterDTO {

    private String employmentType, isGovernmentEmployee, batch, cadre, grade, payScale, designation, rank,
            officeName, govtId, enothiId, location;
    private Date cadreDate, confirmationGODate, gazettedDate, joiningDate, prlDate;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
