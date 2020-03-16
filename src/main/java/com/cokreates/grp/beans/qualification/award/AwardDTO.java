package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AwardDTO extends MasterDTO {

    private String titleOfAward;
    private String awardReceivalPlace;
    private String awardReceivedDate;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String config;
    private String dataStatus;
}
