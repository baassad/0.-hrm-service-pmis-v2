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
@Table(name = "pmis_professional_leave")
public class Leave extends BaseEntity {

    @Column(columnDefinition="varchar(50)")
    private String type;

    @Column(columnDefinition="varchar(50)")
    private String reason;

    @Column(columnDefinition="varchar(50)")
    private String duration;

    private Date fromDate;
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}