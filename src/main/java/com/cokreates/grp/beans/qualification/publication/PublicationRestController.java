package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.qualification.award.AwardDTO;
import com.cokreates.grp.beans.qualification.award.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/publication")
public class PublicationRestController extends MasterRestController<PublicationDTO,Publication> {

    @Autowired
    PublicationService publicationService;

    @Autowired
    public PublicationRestController(PublicationService serviceInterface){
        super(serviceInterface);
    }

    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<PublicationDTO> getAll(@Valid @RequestBody RequestModel<PublicationDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),publicationService.getPublicationList(requestDTO));
    }
}
