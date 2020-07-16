package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeImportRequestDTO extends OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO{

    private List<String> employeeOids;

}
