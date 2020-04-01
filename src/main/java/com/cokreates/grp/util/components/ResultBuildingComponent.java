package com.cokreates.grp.util.components;

import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.MasterDTO;
import com.cokreates.core.ResponseBodyModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeOfficeMasterDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

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


    public ResponseModel<EmployeeOfficeDTO> retrieveResultForEmployeeOffice(DataRequestHeaderModel requestHeaderModel, List<EmployeeOfficeDTO> dtos
    ){

        ResponseModel<EmployeeOfficeDTO> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(new HashMap<>());

        ResponseBodyModel<EmployeeOfficeDTO> responseBodyModel = new ResponseBodyModel<>();
        responseBodyModel.setData(dtos);
        responseModel.setBody(responseBodyModel);

        return responseModel;

    }

    public ResponseModel<EmployeeInformationDTO> retrieveResultForEmployeeInformation(DataRequestHeaderModel requestHeaderModel, List<EmployeeInformationDTO> dtos
    ){

        ResponseModel<EmployeeInformationDTO> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(new HashMap<>());

        ResponseBodyModel<EmployeeInformationDTO> responseBodyModel = new ResponseBodyModel<>();
        responseBodyModel.setData(dtos);
        responseModel.setBody(responseBodyModel);

        return responseModel;

    }

    public ResponseModel<EmployeeOfficeMasterDTO> retrieveResultForEmployeeOfficeMasterDTO(DataRequestHeaderModel requestHeaderModel, List<EmployeeOfficeMasterDTO> dtos
    ){

        ResponseModel<EmployeeOfficeMasterDTO> responseModel = new ResponseModel<>();

        responseModel.setHeader(headerUtilComponent.getResponseHeaderDTO(requestHeaderModel));
        responseModel.setMeta(new HashMap<>());

        ResponseBodyModel<EmployeeOfficeMasterDTO> responseBodyModel = new ResponseBodyModel<>();
        responseBodyModel.setData(dtos);
        responseModel.setBody(responseBodyModel);

        return responseModel;

    }
}
