package com.cokreates.grp.util.webclient;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.EmployeeDetailsDTO;
import com.cokreates.grp.beans.common.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "data-service",url = "http://43.224.110.20:4000/hrm")
public interface DataServiceClient {

    @RequestMapping(method= RequestMethod.POST, value="/get/v1/employee-details", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeDetailsDTO> getEmployeeDetailsFromDaas(@RequestBody DataServiceRequest<EmployeeDTO> requestDTO);

    @RequestMapping(method= RequestMethod.POST, value="/append/v1/employee-office", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<EmployeeOfficeDTO> appendEmployeeOffice(@RequestBody DataServiceRequest<EmployeeOfficeDTO> requestDTO);
}
