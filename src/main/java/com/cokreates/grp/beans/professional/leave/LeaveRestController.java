package com.cokreates.grp.beans.professional.leave;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave")
public class LeaveRestController extends MasterRestController<LeaveDTO,Leave> {

    @Autowired
    public LeaveRestController(LeaveService serviceInterface){
        super(serviceInterface);
    }

}
