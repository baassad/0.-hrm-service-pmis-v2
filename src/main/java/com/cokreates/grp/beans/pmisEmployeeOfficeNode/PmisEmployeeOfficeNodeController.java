package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pmis-employee-office-node")
public class PmisEmployeeOfficeNodeController extends MasterRestController<PmisEmployeeOfficeNodeDTO, PmisEmployeeOfficeNode> {

    @Autowired
    PmisEmployeeOfficeNodeService service;

    public PmisEmployeeOfficeNodeController(PmisEmployeeOfficeNodeService service) {
        super(service);
    }




}
