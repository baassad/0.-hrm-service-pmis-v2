package com.cokreates.grp.beans.common;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeOfficeMasterDTO extends MasterDTO {
    private String email;

    private String phone;

    public String nameEn;

    public String nameBn;

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

    private String isDefaultProfile;

    private String responsibilityType;

    private String isAo;

    private String isDpAuthority;

    private String isDisciplineAdmin;

    private String employeeOfficeOid;

    private String employmentTypeOid;
    private String inchargeLabelBn;
    private String inchargeLabelEn;
    private String isApprover;
    private String isOfficeAdmin;
    private String isOfficeHead;
    private String isOfficeUnitHead;
    private String isReviewer;
    private String joiningDate;
    private String lastOfficeDate;
    private String status;
    private String statusChangeDate;
    private String systemRoleOid;

    private String isAttendanceDataEntryOperator;
    private String isAttendanceAdmin;
}
