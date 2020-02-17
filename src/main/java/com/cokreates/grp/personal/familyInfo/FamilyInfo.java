package com.cokreates.grp.personal.familyInfo;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FamilyInfo extends BaseEntity {

    private String nameEn, nameBn, nationality, profession, dateOfBirth, relation, photo, maritalStatus, hasDisability,
            nid, birthCertificate, Passport, isGovtEmployee;
    //tempData obj
}
