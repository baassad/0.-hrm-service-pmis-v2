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
@Table(name = "pmis_personal_child_education")
public class ChildEducation extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String institutionName;

    @Column(columnDefinition="varchar(100)")
    private String classes;

    @Column(columnDefinition="varchar(100)")
    private String subject;

    @Column(columnDefinition="varchar(100)")
    private String year;

    @Column(columnDefinition="varchar(100)")
    private String result;

    @Column(columnDefinition="text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}
