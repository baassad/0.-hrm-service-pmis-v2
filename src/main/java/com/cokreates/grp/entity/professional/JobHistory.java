package com.cokreates.grp.entity.professional;

import com.cokreates.core.BaseEntity;
import com.cokreates.grp.entity.employeeOffice.EmployeeOffice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@Table(name = "pmis_professional_job_history")
public class JobHistory extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String post;

    @Column(columnDefinition="varchar(100)")
    private String type;

    @Column(columnDefinition="varchar(150)")
    private String officeName;

    @Column(columnDefinition="text")
    private String officeAddress;

    private Date fromDate;
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}