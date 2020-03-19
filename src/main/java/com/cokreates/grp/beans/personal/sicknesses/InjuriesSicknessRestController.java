package com.cokreates.grp.beans.personal.sicknesses;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/injuries-sickness")
public class InjuriesSicknessRestController extends MasterRestController<InjuriesSicknessDTO,InjuriesSickness> {

    @Autowired
    public InjuriesSicknessRestController(InjuriesSicknessService serviceInterface){
        super(serviceInterface);
    }

}
