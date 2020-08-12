package com.cokreates.grp.beans.employeeimport;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.employee.Employee;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.util.request.EmployeeImportRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee-import")
public class EmployeeImportRestController extends MasterRestController<EmployeeDTO, Employee> {


    @Autowired
    EmployeeImportService employeeImportService;

    public EmployeeImportRestController(EmployeeImportService employeeImportService){super(employeeImportService);}

    @PostMapping(Constant.ENDPOINT_BULK_IMPORT_EMPLOYEE_FROM_HRM_V1)
    public ResponseModel<String> importEmployees(@Valid @RequestBody RequestModel<EmployeeImportRequestDTO> requestDTO){

        return resultBuildingComponent.getResponse(requestDTO.getHeader(),employeeImportService.importEmployees(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_IMPORT_OFFICE_ADMIN_FROM_HRM_V1)
    public ResponseModel<String> importOfficeAdmin(@Valid @RequestBody RequestModel<String> requestDTO){

        return resultBuildingComponent.getResponse(requestDTO.getHeader(),employeeImportService.importOfficeAdmin(requestDTO.getBody().getData()));
    }

}
