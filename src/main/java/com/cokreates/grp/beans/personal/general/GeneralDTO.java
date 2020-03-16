package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String maritalStatus;
    private String bloodGroup;
    private String signature;
    private String photo;
    private Date dateOfBirth;
    private String gender;
    private String religion;
    private String nationality;
    private Integer nid;
    private String drivingLicencseNo;
    private String district;
    private String birthPlace;
    private String hasDisability;
    private String height;
    private String isFreedomFighter;
    private String fromFreedomfighterFamily;
    private String fighterDetail;
    private String phone;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
