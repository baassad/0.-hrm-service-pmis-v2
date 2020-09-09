package com.cokreates.grp.beans.employee;

import java.util.Collections;
import java.util.TimeZone;

import javax.validation.Valid;

import com.cokreates.core.*;
import com.cokreates.grp.util.components.ResultBuildingComponent;
import com.cokreates.grp.util.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController extends MasterRestController<EmployeeDTO, Employee> {

    @Autowired
    protected EmployeeService employeeService;

    @Autowired
    protected ResultBuildingComponent<EmptyBodyDTO> emptyResultBuildingComponent;

    public EmployeeRestController(EmployeeService employeeService){
        super(employeeService);
    }

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE)
    public ResponseModel<EmployeeDTO> createEmployee(@Valid @RequestBody RequestModel<GeneralDTO> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(employeeService.create(requestDTO.getBody().getData().get(0))));
    }

    @PostMapping(Constant.ENDPOINT_IMPORT_EMPLOYEE)
    public ResponseModel<EmployeeDTO> importEmployee(@Valid @RequestBody RequestModel<EmployeeCreationDTO> requestDTO){

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(employeeService.importEmployee(requestDTO.getBody().getData().get(0))));
    }

    @PostMapping(Constant.ENDPOINT_IMPORT_EMPLOYEE_FROM_HRM_V1)
    public ResponseModel<EmployeeDTO> getEmployeeResponse(@Valid @RequestBody RequestModel<OidRequestBodyDTO> requestDTO){

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(employeeService.importEmployeeByEmployeeOid(requestDTO)));
    }



    @PostMapping(Constant.ENDPOINT_GET_DETAILS)
    public ResponseModel<EmployeeInformationDTO> getEmployeeInformation(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getEmployeeInformationDTO(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> appendEmployeeOffice(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO) {
        return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(), Collections.singletonList(employeeService.appendEmployeeOfficeDTO(requestDTO.getBody().getData().get(0),requestDTO.getBody().getEmployeeOid())));
    }

    @PostMapping(Constant.ENDPOINT_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> getEmployeeOfficeListForEmployeeAndOfficeUnitPostOid(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO){
        String officeUnitPostOid =  requestDTO.getBody().getData().get(0).getOfficeUnitPostOid();
        if(officeUnitPostOid != null) {
            return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(), employeeService.getEmployeeOfficeList(requestDTO.getBody().getEmployeeOid(),officeUnitPostOid));
        }else {
            return  null;
        }
    }

    @PostMapping(Constant.ENDPOINT_GET_PROFILE)
    public ResponseModel<EmployeeInformationDTO> getEmployeeProfile(@RequestBody RequestModel<MasterDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(), employeeService.getProfileInfo(requestDTO.getBody().getData().get(0).getOid()));
    }


    @PostMapping(Constant.ENDPOINT_GET_DETAILS_BY_OFFICE_OID_LIST)
    public ResponseModel<EmployeeInformationDTO> getEmployeeInformationByOffice(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getEmployeeInformationDTOByOffice(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_GET_ADMIN_BY_OFFICE_OID_LIST)
    public ResponseModel<EmployeeInformationDTO> getAdminByOffice(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getAdminEmployeeInformationDTOByOffice(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_IMPROPER_RESPONSIBILITY_TYPE)
    public ResponseModel<EmployeeInformationDTO> getImproperResponsibilityType(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getImproperResponsibilityType(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_IMPROPER_RESPONSIBILITY_TYPE_BY_EMPLOYEE)
    public ResponseModel<EmployeeInformationDTO> getImproperResponsibilityTypeByEmployee(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getImproperResponsibilityTypeByEmployee(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_GET_MAIN_BY_EMPLOYEE_OID_LIST)
    public ResponseModel<EmployeeInformationDTO> getMainEmployeeInformationByOidSet(@Valid @RequestBody RequestModel<GetListByOidSetRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getEmployeeMainInformationDTOByOidSet(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.ENDPOINT_GET_MAIN_BY_OFFICE_OFFICE_UNIT_OID_LIST)
    public ResponseModel<EmployeeInformationDTO> getMainEmployeeInformationByOffice(@Valid @RequestBody RequestModel<MiscellaneousRequestProperty> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),employeeService.getEmployeeMainInformationDTOByOffice(requestDTO.getBody().getData().get(0)));
    }

    @PostMapping(Constant.SET_TIME_ZONE)
    public ResponseModel<EmptyBodyDTO> setTimeZone(@Valid @RequestBody RequestModel<OidRequestBodyDTO> requestDTO){
        ResponseModel<EmptyBodyDTO> responseModel = emptyResultBuildingComponent.getResponse(requestDTO.getHeader(),employeeService.setTimeZone(requestDTO.getBody().getData().get(0)));
        responseModel.getHeader().setResponseMessage(("Current timezone: " + TimeZone.getDefault()));
        return responseModel;
    }

    @PostMapping(Constant.GET_TIME_ZONE)
    public ResponseModel<EmptyBodyDTO> getTimeZone(@Valid @RequestBody RequestModel<EmptyBodyDTO> requestDTO){
        ResponseModel<EmptyBodyDTO> responseModel = emptyResultBuildingComponent.getResponse(requestDTO.getHeader(),employeeService.getTimeZone(requestDTO.getBody().getData().get(0)));
        responseModel.getHeader().setResponseMessage(("Current timezone: " + TimeZone.getDefault()));
        return responseModel;
    }

}
