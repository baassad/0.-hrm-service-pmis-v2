package com.cokreates.grp.util.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePersonalInfoDTO{

    private String oid;

    private String gender;

    private String maritalStatus;

    private Timestamp dateOfBirth;

    private String birthPlace;

    private String bloodGroup;

    private String etin;

    private String nid;


    private String photoUrl;
    private String signatureUrl;

}
