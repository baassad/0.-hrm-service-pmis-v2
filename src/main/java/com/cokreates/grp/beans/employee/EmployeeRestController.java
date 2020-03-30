package com.cokreates.grp.beans.employee;

import com.cokreates.core.*;
import com.cokreates.grp.beans.common.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.util.request.ActorRequestBodyDTO;
import com.cokreates.grp.util.request.ApprovalHistoryRequestBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController extends MasterRestController<EmployeeDTO, Employee> {

    @Autowired
    protected EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        super(employeeService);
    }

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE)
    public ResponseModel<EmployeeDTO> createEmployee(@Valid @RequestBody RequestModel<GeneralDTO> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(employeeService.create(requestDTO.getBody().getData().get(0))));
    }

    @PostMapping(Constant.ENDPOINT_GET_DETAILS)
    public ResponseModel<EmployeeInformationDTO> getEmployeeInformation(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getEmployeeInformationDTO(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> appendEmployeeOffice(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO) {
        return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(), Collections.singletonList(employeeService.appendEmployeeOfficeDTO(requestDTO.getBody().getData().get(0),requestDTO.getBody().getEmployeeOid())));
    }

}
