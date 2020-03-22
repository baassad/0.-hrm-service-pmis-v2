package com.cokreates.grp.beans.employee;

import com.cokreates.core.BaseEntity;
import com.cokreates.grp.beans.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Employee extends BaseEntity {
    Object personal;
    Object professional;
    Object qualification;
}
