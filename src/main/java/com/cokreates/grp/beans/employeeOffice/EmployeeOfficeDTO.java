package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeOfficeDTO extends MasterDTO {

    private String employmentTypeOid;
    private String inchargeLabelBn;
    private String inchargeLabelEn;
    private String isApprover;
    private String isOfficeAdmin;
    private String isOfficeHead;
    private String isReviewer;
    private String joiningDate;
    private String lastOfficeDate;
    private String officeOid;
    private String officeUnitOid;
    private String officeUnitPostOid;
    private String status;
    private String statusChangeDate;
    private String isDefaultProfile;
    private String isOfficeUnitHead;
    private String responsibilityType;
    private String isAo;
    private String isDpAuthority;
    private String isAttendanceDataEntryOperator;
    private String isAttendanceAdmin;

}
