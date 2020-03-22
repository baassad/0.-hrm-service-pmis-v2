package com.cokreates.grp.beans.employee;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
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

}
