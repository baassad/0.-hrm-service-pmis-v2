package com.cokreates.grp.beans.personal.health;

import java.util.List;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HealthDTO extends MasterDTO {


    private String height;
    private String identifyingMark;
    private String speciallyAbled;
    private List<InjuriesSicknessDTO> injuriesSicknesses;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String config;
    private String dataStatus;
}
