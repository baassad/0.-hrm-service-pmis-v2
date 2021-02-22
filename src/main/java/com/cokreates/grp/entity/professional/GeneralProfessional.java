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
@Table(name = "pmis_professional_general")
public class GeneralProfessional extends BaseEntity {

	@Column(columnDefinition="varchar(50)")
	private String employmentType;

	@Column(columnDefinition="varchar(20)")
	private String isGovernmentEmployee;

	@Column(columnDefinition="varchar(20)")
	private String batch;

	@Column(columnDefinition="varchar(20)")
	private String cadre;

	@Column(columnDefinition="varchar(20)")
	private String grade;

	@Column(columnDefinition="varchar(20)")
	private String payScale;

	@Column(columnDefinition="varchar(150)")
	private String designation;

	@Column(columnDefinition="varchar(20)")
	private String rank;

	@Column(columnDefinition="varchar(150)")
	private String officeName;

	@Column(columnDefinition="varchar(50)")
	private String govtId;

	@Column(columnDefinition="varchar(50)")
	private String enothiId;

	@Column(columnDefinition="text")
	private String location;

	@Column(columnDefinition="varchar(12)")
	private String presentSalary;

	@Column(columnDefinition="varchar(50)")
	private String status;

	@Column(columnDefinition="varchar(50)")
	private String rankType;

	private Date cadreDate;
	private Date confirmationGODate;
	private Date gazettedDate;
	private Date joiningDate;
	private Date prlDate;

	@ManyToOne
	@JoinColumn(name = "employee_office_oid", nullable = false)
	private EmployeeOffice employeeOffice;

}