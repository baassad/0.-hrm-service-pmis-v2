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
@Table(name = "pmis_qualification_award")
public class Award extends BaseEntity {

    @Column(columnDefinition="varchar(150)")
    private String titleOfAward;

    @Column(columnDefinition="text")
    private String awardReceivalPlace;

    @Column(columnDefinition="varchar(50)")
    private String country;

    private Date awardReceivedDate;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}