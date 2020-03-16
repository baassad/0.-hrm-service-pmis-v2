package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String maritalStatus;
    private String bloodGroup;
    private String signature;
    private String photo;
    private String dateOfBirth;
    private String gender;
    private String religion;
    private String nationality;
    private String email;
    private String district;
    private String birthPlace;
    private String hasDisability;
    private String height;
    private String isFreedomFighter;
    private String fromFreedomfighterFamily;
    private String fighterDetail;
    private String phone;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
