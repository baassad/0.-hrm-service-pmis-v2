package com.cokreates.grp.util.webclient;

import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.util.request.EmployeePersonalInfoDTO;
import com.cokreates.grp.util.request.OidRequestBodyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "hrm-service-pim",url = "${hrm-service.url}")
public interface HrmPimClient {

    @RequestMapping(method= RequestMethod.POST, value="/employee-personal-info/v1/get-by-employee-oid", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseModel<EmployeePersonalInfoDTO> getEmployeePersonalInfoByEmployeeOid(ServiceRequestDTO<OidRequestBodyDTO> requestModel);

    @RequestMapping(method= RequestMethod.POST, value="/employee-office/v1/get-active-by-employee-oid", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseModel<EmployeeOfficeDTO> getEmployeeOfficeByEmployeeOid(ServiceRequestDTO<OidRequestBodyDTO> requestModel);


}
