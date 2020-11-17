package com.cokreates.grp.beans.employeeOfficeV2;

import com.cokreates.core.BlankRequestModel;
import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.util.request.EmptyBodyDTO;
import com.cokreates.grp.util.request.OidRequestBodyDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.EMPLOYEE_OFFICE_V2)
public class EmployeeOfficeV2Controller extends MasterRestController<EmployeeOfficeV2DTO, EmployeeOfficeV2> {

    @Autowired
    EmployeeOfficeV2Service service;

    public EmployeeOfficeV2Controller(EmployeeOfficeV2Service service) {
        super(service);
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE_ALL)
    public ResponseModel<EmployeeOfficeV2DTO> createAll(@Valid @RequestBody RequestModel<EmployeeOfficeV2DTO> requestDTO){
    	return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), service.create(requestDTO.getBody().getData()));
    }
    
    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE_ALL)
    public ResponseModel<EmployeeOfficeV2DTO> updateAll(@Valid @RequestBody RequestModel<EmployeeOfficeV2DTO> requestDTO){
    	return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), service.updateAll(requestDTO.getBody().getEmployeeOid(), requestDTO.getBody().getData()));
    }

    @PostMapping(Constant.ENDPOINT_GET_EMPLOYEE_OFFICE_V2_LIST)
    public ResponseModel<EmployeeOfficeV2DTO> getEmployeeOfficeByEmployeeOid(@Valid @RequestBody RequestModel<OidRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), service.getEmployeeOfficeByEmployeeOid(requestDTO.getBody().getEmployeeOid()));
    }
    
    @PostMapping(Constant.ENDPOINT_SYNC_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeV2DTO> syncEmployeeOffice(@Valid @RequestBody BlankRequestModel<EmptyBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), service.syncEmployeeOffice());
    }
}
