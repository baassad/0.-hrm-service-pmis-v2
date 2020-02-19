package com.cokreates.grp.personal.travel;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelRestController extends MasterRestController<TravelDTO,Travel> {
    @Autowired
    public TravelRestController(CklServiceInterface<TravelDTO,Travel> serviceInterface){
        super(serviceInterface);
    }
}
