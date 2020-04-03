package com.cokreates.grp.util.webclient;

import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.util.request.MaritalStatusDTO;
import com.cokreates.grp.util.request.NameRequestBodyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cmn-service-global",url = "${cmn-service-global.url}")
public interface GlobalClient {

    @RequestMapping(method= RequestMethod.POST, value="/marital-status/v1/get-by-name", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseModel<MaritalStatusDTO> getMaritalStatusByName(ServiceRequestDTO<NameRequestBodyDTO> requestDTO);

}
