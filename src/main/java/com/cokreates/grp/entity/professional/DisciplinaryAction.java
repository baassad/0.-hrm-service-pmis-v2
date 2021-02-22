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
@Table(name = "pmis_professional_disciplinary_action")
public class DisciplinaryAction extends BaseEntity {

    @Column(columnDefinition="varchar(50)")
    private String type;

    @Column(columnDefinition="text")
    private String details;

    @Column(columnDefinition="text")
    private String currentSituation;

    @Column(columnDefinition="text")
    private String decision;

    @Column(columnDefinition="varchar(100)")
    private String courtType;

    @Column(columnDefinition="text")
    private String observation;

    private Date dateOfJudgement;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}