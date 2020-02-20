package com.cokreates.grp.util.components;

import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.MasterDTO;
import com.cokreates.core.ResponseBodyModel;
import com.cokreates.core.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResultBuildingComponent<Dto extends MasterDTO> {

    @Autowired
    private HeaderUtilComponent headerUtilComponent;

    public ResponseModel<Dto> retrieveResult(DataRequestHeaderModel requestHeaderModel,Dto dto
                                             ){

        ResponseModel<Dto> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(new HashMap<>());
        //responseModel.setBody(new ResponseBodyModel<>());

        return responseModel;

    }
}
