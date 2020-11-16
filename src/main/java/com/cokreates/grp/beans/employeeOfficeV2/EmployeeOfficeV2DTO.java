package com.cokreates.grp.beans.employeeOfficeV2;

import com.cokreates.core.MasterDTO;
import lombok.Data;

@Data
public class EmployeeOfficeV2DTO extends MasterDTO {
    private String employeeOid;
    private String employeeOfficeOid;
    private String responsibilityType;
    private String officeUnitOid;
    private String officeUnitPostOid;
    private String joiningDate;
    private String employmentTypeOid;
    private String isOfficeHead;
    private String isOfficeUnitHead;
    private String isOfficeAdmin;
    private String isAttendanceAdmin;
    private String isApprover;
    private String isReviewer;
    private String isAttendanceDataEntryOperator;
    private String status;
    private String officeOid;
    private String isAwardAdmin;
    private String inchargeLabelBn;
    private String inchargeLabelEn;
    private String lastOfficeDate;
}
