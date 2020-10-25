package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.pim.pmis.EmployeeGovtId;
import com.cokreates.grp.util.components.ResultBuildingComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/professional-general")
public class ProfessionalGeneralRestController extends MasterRestController<ProfessionalGeneralDTO,ProfessionalGeneral> {


    @Autowired
    ProfessionalGeneralService professionalGeneralService;


    @Autowired
    public ProfessionalGeneralRestController(ProfessionalGeneralService serviceInterface){
        super(serviceInterface);
    }

    @PostMapping(Constant.ENDPOINT_GET_EMPLOYEE_GOVT_ID)
    public ResponseModel<EmployeeGovtId> getGovtIdByEmployeeId(@Valid @RequestBody RequestModel<String> requestModel){
        return resultBuildingComponent.getResponseForEmployeeGovtId(requestModel.getHeader(),professionalGeneralService.getGovtIdByEmployeeOid(requestModel.getBody().getData()));
    }


}
