package com.cokreates.grp.util.components;

import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.MasterDTO;
import com.cokreates.core.ResponseBodyModel;
import com.cokreates.core.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResultBuildingComponent<Dto extends MasterDTO> {

    @Autowired
    private HeaderUtilComponent headerUtilComponent;

    public ResponseModel<Dto> retrieveResult(DataRequestHeaderModel requestHeaderModel, List<Dto> dtos
                                             ){

        ResponseModel<Dto> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(new HashMap<>());

        ResponseBodyModel<Dto> responseBodyModel = new ResponseBodyModel<>();
        responseBodyModel.setData(dtos);
        responseModel.setBody(responseBodyModel);

        return responseModel;

    }


//    public ResponseModel<MasterApprovalDTO> retrieveApprovalResult(DataRequestHeaderModel requestHeaderModel, List<MasterApprovalDTO> dtos
//                                             ){
//
//        ResponseModel<MasterApprovalDTO> responseModel = new ResponseModel<>();
//
//        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
//        responseModel.setMeta(new HashMap<>());
//
//        ResponseBodyModel<MasterApprovalDTO> responseBodyModel = new ResponseBodyModel<>();
//        responseBodyModel.setData(dtos);
//        responseModel.setBody(responseBodyModel);
//
//        return responseModel;
//
//    }

    public ResponseModel<Dto> retrieveResult(DataRequestHeaderModel requestHeaderModel, Map<String,Object> map, List<Dto> dtos){

        ResponseModel<Dto> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(map);

        ResponseBodyModel<Dto> responseBodyModel = new ResponseBodyModel<>();
        responseBodyModel.setData(dtos);
        responseModel.setBody(responseBodyModel);

        return responseModel;


    }
}
