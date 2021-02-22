package com.cokreates.grp.entity.professional;

import java.sql.Date;

import com.cokreates.core.BaseEntity;

import com.cokreates.grp.entity.employeeOffice.EmployeeOffice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@Table(name = "pmis_professional_service_history")
public class ServiceHistory extends BaseEntity {
    
    private Date govtServiceDate;
    private Date gazettedDate;
    private Date encadrementDate;
    private Date seniorityDate;

    @Column(columnDefinition="varchar(50)")
    private String cadre;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}