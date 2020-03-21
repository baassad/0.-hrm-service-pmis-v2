package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.util.components.ResultBuildingComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/general")
public class GeneralRestController extends MasterRestController<GeneralDTO,General> {

    public GeneralRestController(GeneralService serviceInterface){
        super(serviceInterface);
    }

    @Autowired
    private ResultBuildingComponent resultBuildingComponent;

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE)
    public ResponseModel<GeneralDTO> createEmployee(@Valid @RequestBody RequestModel<GeneralDTO> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(service.create(requestDTO.getBody().getData().get(0))));
    }

}