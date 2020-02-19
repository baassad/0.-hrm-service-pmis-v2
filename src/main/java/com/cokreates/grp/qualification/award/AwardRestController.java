package com.cokreates.grp.qualification.award;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/award")
public class AwardRestController extends MasterRestController<AwardDTO,Award> {

    public AwardRestController(CklServiceInterface<AwardDTO,Award> serviceInterface){
        super(serviceInterface);
    }
}
