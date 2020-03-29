package com.cokreates.grp.beans.personal.familyInfo;

import java.sql.Date;

import com.cokreates.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FamilyInfo extends BaseEntity {

    private String nameEn, nameBn, nationality, profession, relation, photo, maritalStatus, hasDisability,
            nid, birthCertificate, Passport, isGovtEmployee,gender,address,organization,designation,location;
    private Date dateOfBirth;
}
