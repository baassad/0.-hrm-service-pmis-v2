package com.cokreates.grp.beans.personal.familyInfo;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.personal.address.AddressDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
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
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
