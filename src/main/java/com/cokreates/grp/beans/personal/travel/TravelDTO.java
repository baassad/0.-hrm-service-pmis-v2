package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TravelDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String purpose;
    private String from;
    private String to;
    private String type;
    private String govtOrderNo, govtOrderDate;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
