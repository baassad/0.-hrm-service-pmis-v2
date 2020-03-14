package com.cokreates.grp.beans.personal.general;

import java.util.Date;

import com.cokreates.core.MasterDTO;

import lombok.Data;

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
}
