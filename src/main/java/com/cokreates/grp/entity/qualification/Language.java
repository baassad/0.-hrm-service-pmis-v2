package com.cokreates.grp.entity.qualification;

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
@Table(name = "pmis_qualification_language")
public class Language extends BaseEntity {

    @Column(columnDefinition="varchar(30)")
    private String languageName;

    @Column(columnDefinition="varchar(10)")
    private String isNativeLanguage;

    @Column(columnDefinition="varchar(20)")
    private String readingSkill;

    @Column(columnDefinition="varchar(20)")
    private String writingSkill;

    @Column(columnDefinition="varchar(20)")
    private String speakingSkill;

    @Column(columnDefinition="varchar(20)")
    private String listeningSkill;

    @ManyToOne
    @JoinColumn(name = "employee_office_oid", nullable = false)
    private EmployeeOffice employeeOffice;

}