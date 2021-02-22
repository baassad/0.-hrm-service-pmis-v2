package com.cokreates.grp.entity.personal;

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
@Table(name = "pmis_personal_bank")
public class Bank extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String nameBn;

    @Column(columnDefinition="varchar(150)")
    private String branch;

    @Column(columnDefinition="varchar(50)")
    private String accountNumber;

    @Column(columnDefinition="varchar(30)")
    private String typeOfAccount;

    @Column(columnDefinition="varchar(150)")
    private String nameOfAccount;

    @Column(columnDefinition="varchar(50)")
    private String currentCondition;

    @Column(columnDefinition="varchar(100)")
    private String bankType;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}