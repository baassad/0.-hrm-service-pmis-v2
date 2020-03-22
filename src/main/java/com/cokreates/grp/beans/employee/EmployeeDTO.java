package com.cokreates.grp.beans.employee;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDTO extends MasterDTO {
    Object personal;
    Object professional;
    Object qualification;
}