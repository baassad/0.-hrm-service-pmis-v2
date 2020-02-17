package com.cokreates.grp.personal.familyInfo;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class FamilyInfo extends BaseEntity {

    private String nameEn, nameBn, nationality, profession, relation, photo, maritalStatus, hasDisability,
            nid, birthCertificate, Passport, isGovtEmployee;
    private Date dateOfBirth;
    //tempData obj
}
