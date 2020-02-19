package com.cokreates.grp.qualification.publication;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publication")
public class PublicationRestController extends MasterRestController<PublicationDTO,Publication> {

    @Autowired
    public PublicationRestController(CklServiceInterface<PublicationDTO,Publication> serviceInterface){
        super(serviceInterface);
    }
}
