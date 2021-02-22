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
@Table(name = "pmis_personal_publication")
public class Publication extends BaseEntity {

    @Column(columnDefinition="varchar(50)")
    private String publicationType;

    @Column(columnDefinition="varchar(50)")
    private String publicationName;

    @Column(columnDefinition="text")
    private String description;

    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}