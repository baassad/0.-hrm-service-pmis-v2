package com.cokreates.grp.beans.personal.health;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthRestController extends MasterRestController<HealthDTO,Health> {

    @Autowired
    public HealthRestController(HealthService serviceInterface){
        super(serviceInterface);
    }

}
