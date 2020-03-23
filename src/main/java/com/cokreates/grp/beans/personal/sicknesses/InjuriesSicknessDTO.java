package com.cokreates.grp.beans.personal.sicknesses;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class InjuriesSicknessDTO extends MasterDTO {

    private String description;
    private String type;
    private Date from;
    private Date to;
    private String comment;
    private String phisicallyImpairment,stability;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
