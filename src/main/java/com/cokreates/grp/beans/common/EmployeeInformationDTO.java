package com.cokreates.grp.beans.common;

import lombok.Data;

@Data
public class EmployeeInformationDTO{

    private String oid;

    private String email;

    private String mobileNo;

    public String nameEn;

    public String nameBn;

    public String userName;

    private String officeOid;

    private String officeUnitOid;

    private String officeUnitPostOid;

    public String officeNameEn;

    public String officeNameBn;

    public String officeUnitNameEn;

    public String officeUnitNameBn;

    public String officeUnitPostNameEn;

    public String officeUnitPostNameBn;

    private String profileImageOid;

    private String signatureImageOid;

    private String employeeOfficeOid;

    private String employeeTypeOid;

    private String isDefaultProfile;

    private String responsibilityType;

}
