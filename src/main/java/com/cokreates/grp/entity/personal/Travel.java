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
@Table(name = "pmis_personal_travel")
public class Travel extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String countryNameEn;

    @Column(columnDefinition="varchar(150)")
    private String countryNameBn;

    @Column(columnDefinition="text")
    private String purpose;

    private Date from;
    private Date to;
    private Date govtOrderDate;

    @Column(columnDefinition="varchar(150)")
    private String govtOrderNo;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}