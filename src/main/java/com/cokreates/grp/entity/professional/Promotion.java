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
@Table(name = "pmis_professional_promotion")
public class Promotion extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String gono;

    @Column(columnDefinition="varchar(100)")
    private String natureOfPromotion;

    @Column(columnDefinition="varchar(100)")
    private String payScaleToWhichPromoted;

    @Column(columnDefinition="varchar(50)")
    private String rank;

    @Column(columnDefinition="varchar(50)")
    private String grade;

    private Date dateOfPromotion;
    private Date dateOfGO;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}
