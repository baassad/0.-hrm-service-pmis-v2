package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.personal.file.FileDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private String isAttendanceAdmin;

    private String isAttendanceDataEntryOperator;

    private String responsibilityType;

    private String isAo;

    private String isDpAuthority;

    private byte[] photo;

    private List<FileDTO> photoFileDTOs;

}
