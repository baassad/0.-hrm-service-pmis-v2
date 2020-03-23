package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceHistoryDTO extends MasterDTO {

    private String post;
    private String type;
    private String from;
    private String to;
    
    private String govtServiceDate, gazettedDate, encadrementDate, seniorityDate, cadre;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
