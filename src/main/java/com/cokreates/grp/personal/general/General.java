package com.cokreates.grp.personal.general;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class General extends BaseEntity {

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
    private String nid;
    private String drivingLicencseNo;
    private String district;
    private String birthPlace;
    private String hasDisability;
    private String height;
    private String isFreedomFighter;
    private String fromFreedomfighterFamily;
    private String fighterDetail;
    private String phone;
    /*private String rowStatus;
    private String createdBy;
    private String createdOn;
    private String updatedBy;
    private String updatedOn;
    private String config;*/

    //tempData obj
}