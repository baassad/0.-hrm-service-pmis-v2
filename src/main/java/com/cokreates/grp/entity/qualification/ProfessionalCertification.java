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
@Table(name = "pmis_qualification_professional_certification")
public class ProfessionalCertification extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String title;

    @Column(columnDefinition="varchar(150)")
    private String institution;

    @Column(columnDefinition="varchar(50)")
    private String duration;

    @Column(columnDefinition="varchar(50)")
    private String country;

    @Column(columnDefinition="text")
    private String comment;

    private Date receivalDate;
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}