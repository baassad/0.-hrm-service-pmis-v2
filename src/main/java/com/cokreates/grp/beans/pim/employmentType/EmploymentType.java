package com.cokreates.grp.beans.pim.employmentType;

import com.cokreates.grp.beans.pim.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class EmploymentType extends SuperEntity {

    private String nameEn;
    private String nameBn;

}
