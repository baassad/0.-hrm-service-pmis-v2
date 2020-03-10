package com.cokreates.grp.util.webclient;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "data-service",url = "http://13.224.110.22:80/hrm")
public interface DataServiceClient<D extends MasterDTO> {

    @RequestMapping(method= RequestMethod.POST, value="/create/v1/emp", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<D> createEmployee(@RequestBody DataServiceRequest<D> requestDTO);

    @RequestMapping(method = RequestMethod.POST,value = "/get/v1/node-in-emp-doc" ,consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    DataServiceResponse<D> getDataFromParticularNode(@RequestBody DataServiceRequest<D> requestDTO);
}
