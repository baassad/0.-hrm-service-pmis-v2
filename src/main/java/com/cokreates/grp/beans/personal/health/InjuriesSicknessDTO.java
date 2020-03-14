package com.cokreates.grp.beans.personal.health;

import java.util.Date;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InjuriesSicknessDTO extends MasterDTO {

    private String description;
    private String type;
    private Date from;
    private Date to;
    private String comment;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
