package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.util.request.OidRequestBodyDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.PMIS_EMPLOYEE_OFFICE_NODE)
public class PmisEmployeeOfficeNodeController extends MasterRestController<PmisEmployeeOfficeNodeDTO, PmisEmployeeOfficeNode> {

    @Autowired
    PmisEmployeeOfficeNodeService service;

    public PmisEmployeeOfficeNodeController(PmisEmployeeOfficeNodeService service) {
        super(service);
    }

    @PostMapping(Constant.ENDPOINT_GET_EMPLOYEE_OFFICE_NODE_LIST)
    public ResponseModel<PmisEmployeeOfficeNodeDTO> getPmisEmployeeOfficeNodes(@Valid @RequestBody RequestModel<OidRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), service.getPmisEmployeeOfficeNodes(requestDTO.getBody().getEmployeeOid()));
    }

}
