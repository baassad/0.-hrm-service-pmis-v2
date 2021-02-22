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
@Table(name = "pmis_professional_posting")
public class Posting extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String post;

    @Column(columnDefinition="varchar(150)")
    private String organization;

    @Column(columnDefinition="text")
    private String location;

    @Column(columnDefinition="varchar(50)")
    private String payScale;

    @Column(columnDefinition="varchar(50)")
    private String payLastDrawn;

    @Column(columnDefinition="varchar(50)")
    private String district;

    @Column(columnDefinition="varchar(50)")
    private String city;

    @Column(columnDefinition="varchar(50)")
    private String grade;

    private Date fromDate;
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}
