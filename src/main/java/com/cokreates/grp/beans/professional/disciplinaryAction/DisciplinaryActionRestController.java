package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.MasterRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinary-action")
public class DisciplinaryActionRestController extends MasterRestController<DisciplinaryActionDTO,DisciplinaryAction> {

    public DisciplinaryActionRestController(DisciplinaryActionService serviceInterface){
        super(serviceInterface);
    }
}
