package com.cokreates.grp.beans.employee;

import com.cokreates.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Employee extends BaseEntity {
    Object personal;
    Object professional;
    Object qualification;
}
