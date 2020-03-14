package com.cokreates.grp.beans.personal.health;

import com.cokreates.core.MasterDTO;

import java.util.Date;
import java.util.List;

public class HealthDTO extends MasterDTO {


    private String height;
    private String identifyingMark;
    private String speciallyAbled;
    private List<InjuriesSicknessDTO> injuriesSicknesses;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
