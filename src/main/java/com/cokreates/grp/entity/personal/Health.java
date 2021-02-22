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
@Table(name = "pmis_personal_health")
public class Health extends BaseEntity {

    @Column(columnDefinition="varchar(30)")
    private String height;

    @Column(columnDefinition="varchar(200)")
    private String identifyingMark;

    @Column(columnDefinition="varchar(200)")
    private String speciallyAbled;

    @Column(columnDefinition="varchar(30)")
    private String weight;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}
