package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/award")
public class AwardRestController extends MasterRestController<AwardDTO,Award> {

    @Autowired
    AwardService awardService;

    public AwardRestController(AwardService serviceInterface){
        super(serviceInterface);
    }

    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<AwardDTO> getAll(@Valid @RequestBody RequestModel<AwardDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),awardService.getAwardList(requestDTO));
    }

}
