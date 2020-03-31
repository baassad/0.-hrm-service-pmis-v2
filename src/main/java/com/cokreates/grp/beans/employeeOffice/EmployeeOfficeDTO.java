package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.MasterDTO;
import lombok.Data;

@Data
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
            private String systemRoleOid;

}
