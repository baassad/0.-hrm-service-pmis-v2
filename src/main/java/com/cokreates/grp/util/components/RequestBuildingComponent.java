package com.cokreates.grp.util.components;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestBuildingComponent<Dto extends MasterDTO> {

     public DataServiceRequest<Dto> getRequestForRead(List<String> nodePath,Dto node,String oid){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(oid);
        requestBody.setNode(node);
        requestBody.setNodePath(nodePath);

        request.setBody(requestBody);

        return request;
     }

}
