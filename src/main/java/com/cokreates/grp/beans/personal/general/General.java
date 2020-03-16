package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class General extends BaseEntity {

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

}