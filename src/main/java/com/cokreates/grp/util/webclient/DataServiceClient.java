package com.cokreates.grp.util.webclient;

import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeDetailsDTO;

import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.employee.EmployeeCreationDTO;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.search.DataServiceEmployeeSearchDTO;
import com.cokreates.grp.beans.search.EmployeeDetails;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.daas.DataServiceResponseForList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "data-service",url = "${spring.application.gdata_end_point_url}")
public interface DataServiceClient {

    @RequestMapping(method= RequestMethod.POST, value="/pmis/get/v1/employee-details", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeDetailsDTO> getEmployeeDetailsFromDaas(@RequestBody DataServiceRequest<EmployeeDTO> requestDTO);

    @RequestMapping(method= RequestMethod.POST, value="/pmis/append/v1/employee-office", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeOfficeDTO> appendEmployeeOffice(@RequestBody DataServiceRequest<EmployeeOfficeDTO> requestDTO);

    @RequestMapping(method= RequestMethod.POST, value="/pmis/update/v1/employee-office", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeOfficeDTO> updateEmployeeOffice(@RequestBody DataServiceRequest<EmployeeOfficeDTO> requestDTO);

    @RequestMapping(method = RequestMethod.POST,  value = "/pmis/get/v1/employee-office", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponseForList<EmployeeOfficeDTO> getEmployeeOfficeList(@RequestBody DataServiceRequest<EmployeeDTO> requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/pmis/import/v1/emp",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeDTO> importEmployee (@RequestBody DataServiceRequest<EmployeeCreationDTO> requestDTO);

    @RequestMapping(method = RequestMethod.POST,value = "/pmis/v1/get-employees-by-name" ,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseModel<EmployeeDetails> getEmployees (@RequestBody ServiceRequestDTO<DataServiceEmployeeSearchDTO> requestDTO);
}
