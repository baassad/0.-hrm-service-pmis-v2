package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DisciplinaryActionDTO extends MasterDTO {

	private String type, details, currentSituation, decision, courtType, observation;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
