package com.cokreates.grp.util.webclient;

import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.util.request.OidSetWithStrictnessRequestBodyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cmn-service-organogram",url = "${cmn-service-organogram.url}")
public interface OrganogramClient {

    @RequestMapping(method= RequestMethod.POST, value="/search/v1/get-list-by-oid-set", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseModel<EmployeeInformationDTO> getEmployeeInformationFromOrganogram(ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> requestDTO);
}
