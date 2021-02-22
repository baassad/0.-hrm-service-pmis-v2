package com.cokreates.grp.entity.qualification;

import com.cokreates.core.BaseEntity;
import com.cokreates.grp.entity.employeeOffice.EmployeeOffice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@Table(name = "pmis_personal_training")
public class Training extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String subjectName;

    @Column(columnDefinition="varchar(150)")
    private String institution;

    @Column(columnDefinition="varchar(50)")
    private String country;

    @Column(columnDefinition="varchar(50)")
    private String grade;

    @Column(columnDefinition="varchar(150)")
    private String position;

    @Column(columnDefinition="varchar(150)")
    private String fundingSource;

    private Date startedFrom;
    private Date endedOn;

    @Type(type = "jsonb")
    @Column(name = "subject_type", columnDefinition = "jsonb")
    private Set<String> subjectType;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}
