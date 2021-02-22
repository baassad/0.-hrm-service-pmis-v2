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
@Table(name = "pmis_personal_general")
public class GeneralPersonal extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
	private String nameEn;

    @Column(columnDefinition="varchar(150)")
    private String nameBn;

    @Column(columnDefinition="varchar(25)")
    private String maritalStatus;

    @Column(columnDefinition="varchar(30)")
    private String bloodGroup;

    private Date dateOfBirth;

    @Column(columnDefinition="varchar(20)")
    private String gender;

    @Column(columnDefinition="varchar(20)")
    private String religion;

    @Column(columnDefinition="varchar(30)")
    private String nationality;

    @Column(columnDefinition="varchar(150)")
    private String email;

    @Column(columnDefinition="varchar(20)")
    private String isFreedomFighter;

    @Column(columnDefinition="varchar(30)")
    private String fromFreedomfighterFamily;

    @Column(columnDefinition="varchar(20)")
    private String phone;

    @Column(columnDefinition="varchar(25)")
    private String nid;

    @Column(columnDefinition="varchar(30)")
    private String passport;

    @Column(columnDefinition="varchar(25)")
    private String birthCertificate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}