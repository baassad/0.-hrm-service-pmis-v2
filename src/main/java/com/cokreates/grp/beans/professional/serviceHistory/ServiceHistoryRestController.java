package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-history")
public class ServiceHistoryRestController extends MasterRestController<ServiceHistoryDTO,ServiceHistory> {

    @Autowired
    public ServiceHistoryRestController(ServiceHistoryService serviceInterface){
        super(serviceInterface);
    }
}
