package com.cokreates.grp.beans.employeeOfficeV2;

import com.cokreates.core.BaseEntity;
import com.cokreates.core.Constant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employee_office_v2")
public class EmployeeOfficeV2 extends BaseEntity {
    private String employeeOid;
    private String employeeOfficeOid;
    private String officeOid;
    private String officeUnitOid;
    private String officeUnitPostOid;
    private String employmentTypeOid;
    private String inchargeLabelBn;
    private String inchargeLabelEn;
    private String joiningDate;
    private String lastOfficeDate;
    private String status;
    private String responsibilityType;
    private String isApprover = Constant.NO;
    private String isReviewer = Constant.NO;
    private String isOfficeAdmin = Constant.NO;
    private String isAttendanceAdmin = Constant.NO;
    private String isAttendanceDataEntryOperator = Constant.NO;
    private String isAwardAdmin = Constant.NO;
    private String isOfficeHead = Constant.NO;
    private String isOfficeUnitHead = Constant.NO;
}
