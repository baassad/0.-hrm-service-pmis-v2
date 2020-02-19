package com.cokreates.grp.professional.general;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professional-general")
public class GeneralRestController extends MasterRestController<GeneralDTO,General> {

    @Autowired
    public GeneralRestController(CklServiceInterface<GeneralDTO,General> serviceInterface){
        super(serviceInterface);
    }

}
