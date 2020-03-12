package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankRestController extends MasterRestController<BankDTO,Bank> {

    @Autowired
    public BankRestController(BankService bankInterface){

        super(bankInterface);
    }

}
