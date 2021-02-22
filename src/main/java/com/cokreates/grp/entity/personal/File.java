package com.cokreates.grp.entity.personal;

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
@Table(name = "pmis_personal_file")
public class File extends BaseEntity {

    @Column(columnDefinition="varchar(100)")
    private String attachmentId;

    @Column(columnDefinition="varchar(100)")
    private String type;

    private Date issueDate;
    private Date expiryDate;

    @Column(columnDefinition="varchar(100)")
    private String fileOid;

    @Column(columnDefinition="varchar(200)")
    private String fileName;

    @Column(columnDefinition="varchar(20)")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}