package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/general")
public class GeneralRestController extends MasterRestController<GeneralDTO,General> {

    public GeneralRestController(GeneralService serviceInterface){
        super(serviceInterface);
    }
    
    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<GeneralDTO> getAll(@RequestBody RequestModel<GeneralDTO> requestDTO) {
    	return super.getAll(requestDTO);
    }
}
