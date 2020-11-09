package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.MasterDTO;
import lombok.Data;

@Data
public class PmisEmployeeOfficeNodeDTO extends MasterDTO {
    private String pmisOid;
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
}
