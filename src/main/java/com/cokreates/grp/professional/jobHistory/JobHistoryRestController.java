package com.cokreates.grp.professional.jobHistory;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-history")
public class JobHistoryRestController extends MasterRestController<JobHistoryDTO,JobHistory> {

    @Autowired
    public JobHistoryRestController(CklServiceInterface<JobHistoryDTO,JobHistory> serviceInterface){
        super(serviceInterface);
    }
}
