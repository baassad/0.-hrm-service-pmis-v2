package com.cokreates.grp.beans.professional.jobHistory;


import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobHistoryDTO extends MasterDTO {

    private String post;
    private String type;
    private String from;
    private String to;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
