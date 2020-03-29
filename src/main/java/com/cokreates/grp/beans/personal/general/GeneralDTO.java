package com.cokreates.grp.beans.personal.general;

import java.sql.Date;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String maritalStatus;
    private String bloodGroup;
    private Date dateOfBirth;
    private String gender;
    private String religion;
    private String nationality;
    private String email;
    private String isFreedomFighter;
    private String fromFreedomfighterFamily;
    private String phone;

}