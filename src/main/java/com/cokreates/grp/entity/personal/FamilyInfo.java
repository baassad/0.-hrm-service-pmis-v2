package com.cokreates.grp.entity.personal;

import java.sql.Date;

import com.cokreates.core.BaseEntity;

import com.cokreates.grp.entity.employeeOffice.EmployeeOffice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@Table(name = "pmis_personal_family_info")
public class FamilyInfo extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String nameEn;

    @Column(columnDefinition="varchar(150)")
    private String nameBn;

    @Column(columnDefinition="varchar(100)")
    private String nationality;

    @Column(columnDefinition="varchar(150)")
    private String profession;

    @Column(columnDefinition="varchar(100)")
    private String relation;

    @Column(columnDefinition="varchar(250)")
    private String photo;

    @Column(columnDefinition="varchar(25)")
    private String maritalStatus;

    @Column(columnDefinition="varchar(20)")
    private String hasDisability;

    @Column(columnDefinition="varchar(30)")
    private String nid;

    @Column(columnDefinition="varchar(30)")
    private String birthCertificate;

    @Column(columnDefinition="varchar(30)")
    private String Passport;

    @Column(columnDefinition="varchar(20)")
    private String isGovtEmployee;

    @Column(columnDefinition="varchar(20)")
    private String gender;

    @Column(columnDefinition="text")
    private String address;

    @Column(columnDefinition="varchar(150)")
    private String organization;

    @Column(columnDefinition="varchar(150)")
    private String designation;

    @Column(columnDefinition="text")
    private String location;

    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}
