package com.cokreates.grp.entity.qualification;

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
@Table(name = "pmis_qualification_education")
public class Education extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String degree;

    @Column(columnDefinition="varchar(150)")
    private String subject;

    @Column(columnDefinition="varchar(150)")
    private String institution;

    @Column(columnDefinition="varchar(20)")
    private String isForeignInstitution;

    @Column(columnDefinition="varchar(50)")
    private String result;

    @Column(columnDefinition="varchar(100)")
    private String achieved;

    @Column(columnDefinition="varchar(50)")
    private String scale;

    @Column(columnDefinition="varchar(100)")
    private String country;

    private Date startingDate;
    private Date endingDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;
}
