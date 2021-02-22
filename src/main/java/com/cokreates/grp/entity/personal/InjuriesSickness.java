package com.cokreates.grp.entity.personal;

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
@Table(name = "pmis_personal_injuries_sickness")
public class InjuriesSickness extends BaseEntity {

    @Column(columnDefinition="text")
    private String description;

    @Column(columnDefinition="varchar(50)")
    private String type;

    private Date from;
    private Date to;

    @Column(columnDefinition="text")
    private String comment;

    @Column(columnDefinition="varchar(200)")
    private String phisicallyImpairment;

    @Column(columnDefinition="varchar(50)")
    private String stability;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}