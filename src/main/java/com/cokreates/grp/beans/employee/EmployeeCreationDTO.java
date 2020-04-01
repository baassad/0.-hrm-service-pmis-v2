package com.cokreates.grp.beans.employee;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployeeCreationDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String gender;
    private String religion;
    private String maritalStatus;
    private Timestamp dateOfBirth;
    private String phone;
    private String  email;
    private String employeeTypeOid;
    private String officeOid;
    private String officeUnitOid;
    private String officeUnitPostOid;
    private String responsibilityType;
    private Timestamp joiningDate;
    private String isOfficeHead;
    private String isOfficeAdmin;
    private String isOfficeUnitHead;

    private String employeeOfficeOid;
}
