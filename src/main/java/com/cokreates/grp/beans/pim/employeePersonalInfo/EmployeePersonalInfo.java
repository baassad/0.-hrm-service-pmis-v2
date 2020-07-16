package com.cokreates.grp.beans.pim.employeePersonalInfo;

import com.cokreates.grp.beans.pim.base.SuperEntity;
import com.cokreates.grp.beans.pim.employeeMasterInfo.EmployeeMasterInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class EmployeePersonalInfo extends SuperEntity {

    private String fatherNameEn;
    private String fatherNameBn;
    private String motherNameEn;
    private String motherNameBn;
    private String emailAddress;
    private String mobileNo;
    private String phoneNo;
    private String bloodGroup;
    private String birthPlace;
    private String gender;
    private String maritalStatus;
    private String etin;
    private String nid;
    private String bankAccount;


    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "employee_oid")
    private EmployeeMasterInfo employeeMasterInfo;

}
