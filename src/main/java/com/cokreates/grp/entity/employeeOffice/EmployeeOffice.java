package com.cokreates.grp.entity.employeeOffice;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
public class EmployeeOffice extends BaseEntity {

    @Column(columnDefinition="varchar(50)")
    private String employmentTypeOid;

    @Column(columnDefinition="varchar(150)")
    private String inchargeLabelBn;

    @Column(columnDefinition="varchar(150)")
    private String inchargeLabelEn;

    @Column(columnDefinition="varchar(10)")
    private String isApprover;

    @Column(columnDefinition="varchar(10)")
    private String isOfficeAdmin;

    @Column(columnDefinition="varchar(10)")
    private String isOfficeHead;

    @Column(columnDefinition="varchar(10)")
    private String isReviewer;

    private Date joiningDate;
    private Date lastOfficeDate;

    @Column(columnDefinition="varchar(50)")
    private String officeOid;

    @Column(columnDefinition="varchar(50)")
    private String officeUnitOid;

    @Column(columnDefinition="varchar(50)")
    private String officeUnitPostOid;

    @Column(columnDefinition="varchar(50)")
    private String status;

    private Date statusChangeDate;

    @Column(columnDefinition="varchar(50)")
    private String systemRoleOid;

    @Column(columnDefinition="varchar(10)")
    private String isAwardAdmin;
}
