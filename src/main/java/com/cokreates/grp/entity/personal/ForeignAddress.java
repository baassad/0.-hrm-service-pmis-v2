package com.cokreates.grp.entity.personal;

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
@Table(name = "pmis_personal_foreign_address")
public class ForeignAddress extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String houseRoadBlockSector;

    @Column(columnDefinition="varchar(100)")
    private String stateProvinceRegion;

    @Column(columnDefinition="varchar(50)")
    private String zipPostalCode;

    @Column(columnDefinition="varchar(50)")
    private String city;

    @Column(columnDefinition="varchar(100)")
    private String country;

    @Column(columnDefinition="varchar(30)")
    private String phone;

    @Column(columnDefinition="varchar(150)")
    private String email;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}