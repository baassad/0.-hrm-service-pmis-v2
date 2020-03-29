package com.cokreates.grp.beans.employee;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDTO extends MasterDTO {
    Object personal;
    Object professional;
    Object qualification;
}