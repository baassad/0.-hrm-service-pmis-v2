package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
public class TravelRestController extends MasterRestController<TravelDTO,Travel> {
    @Autowired
    public TravelRestController(CklServiceInterface<TravelDTO,Travel> serviceInterface){
        super(serviceInterface);
    }
}
