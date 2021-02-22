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
@Table(name = "pmis_personal_address")
public class Address extends BaseEntity {

    @Column(columnDefinition="varchar(20)")
    private String road; //houseRoadBlockSector (emergencyContactAddress, birthPlaceAddress)

    @Column(columnDefinition="varchar(150)")
    private String village; //villageColonyWard (emergencyContactAddress, birthPlaceAddress)

    @Column(columnDefinition="varchar(150)")
    private String postOffice;

    @Column(columnDefinition="varchar(20)")
    private String postCode;

    @Column(columnDefinition="varchar(150)")
    private String thana;

    @Column(columnDefinition="varchar(150)")
    private String district;

    @Column(columnDefinition="varchar(150)")
    private String division;

    @Column(columnDefinition="varchar(150)")
    private String country;

    @Column(columnDefinition="varchar(20)")
    private String phone;

    @Column(columnDefinition="varchar(150)")
    private String email;

    @Column(columnDefinition="varchar(50)")
    private String addressType; //Present/Permanent/EmergencyContactAddress/BirthPlaceAddress

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}