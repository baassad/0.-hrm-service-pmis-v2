package com.cokreates.grp.beans.personal.familyInfo;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FamilyInfoDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String nationality;
    private String profession;
    private String relation;
    private String photo;
    private String maritalStatus;
    private String hasDisability;
    private String nid;
    private String birthCertificate;
    private String passport;
    private String isGovtEmployee;
    private String dateOfBirth;
    private String gender,address,organization,designation,location;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
