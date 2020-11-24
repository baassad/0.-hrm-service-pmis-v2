package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterRestController;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.grp.beans.common.EmployeeAndJsonOidHolderRequestBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankRestController extends MasterRestController<BankDTO,Bank> {

    @Autowired
    BankService bankService;

    @Autowired
    public BankRestController(BankService bankInterface){

        super(bankInterface);
    }

    @PostMapping(value = "/accounts/get-by-oid")
    public ResponseModel<BankDTO> getBankAccountByAccountOid(@Valid @RequestBody RequestModel<EmployeeAndJsonOidHolderRequestBodyDTO> requestDTO){
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),bankService.getBankAccountByOid(requestDTO.getBody().getData().get(0)));
    }

}
