package com.cokreates.grp.beans.pim.employeeOfficePim;

import com.cokreates.grp.beans.pim.base.SuperEntity;
import com.cokreates.grp.beans.pim.employeeMasterInfo.EmployeeMasterInfo;
import com.cokreates.grp.beans.pim.employmentType.EmploymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class EmployeeOffice extends SuperEntity {

    private String systemRoleId;
    private String inchargeLabelEn;
    private String inchargeLabelBn;
    private String isDefaultProfile;
    private String isOfficeAdmin;
    private String isOfficeHead;
    private String nodeLevel;
    private String nodeSequence;
    private String officeEmail;
    private String officePhone;
    private String status;
    private String responsibilityType;

    private Date joiningDate;

    private Date lastOfficeDate;

    private Date statusChangeDate;

    @ManyToOne
    @JoinColumn(name = "employee_oid")
    private EmployeeMasterInfo employeeMasterInfo;

    @ManyToOne
    private EmploymentType employmentType;

    @Column(name = "office_id")
    private String officeOid;

    @Column(name = "office_unit_id")
    private String officeUnitOid;

    @Column(name = "office_unit_post_id")
    private String officeUnitPostOid;
}
