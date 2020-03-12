package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinary-action")
public class DisciplinaryActionRestController extends MasterRestController<DisciplinaryActionDTO,DisciplinaryAction> {

    @Autowired
    public DisciplinaryActionRestController(CklServiceInterface<DisciplinaryActionDTO,DisciplinaryAction> serviceInterface){
        super(serviceInterface);
    }
}
