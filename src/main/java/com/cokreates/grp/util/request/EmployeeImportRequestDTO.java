package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.Set;

@Data
public class EmployeeImportRequestDTO extends OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO{

    private Set<String> employeeOids;

}
