package com.cokreates.grp.beans.personal.familyInfo;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.util.Date;

@Data
public class FamilyInfoDTO extends MasterDTO {

    private String nameEn;
    private String  nameBn;
    private String nationality;
    private String profession;
    private String relation;
    private String photo;
    private String maritalStatus;
    private String  hasDisability;
    private String nid;
    private String birthCertificate;
    private String passport;
    private String isGovtEmployee;
    private Date dateOfBirth;
}
