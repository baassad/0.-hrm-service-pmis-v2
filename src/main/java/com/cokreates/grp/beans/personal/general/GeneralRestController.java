package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/general")
public class GeneralRestController extends MasterRestController<GeneralDTO,General> {

    public GeneralRestController(GeneralService serviceInterface){
        super(serviceInterface);
    }
}
