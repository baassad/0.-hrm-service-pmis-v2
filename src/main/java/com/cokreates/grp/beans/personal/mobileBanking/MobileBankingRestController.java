package com.cokreates.grp.beans.personal.mobileBanking;

import com.cokreates.core.MasterRestController;
import com.cokreates.grp.beans.personal.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile-banking")
public class MobileBankingRestController extends MasterRestController<MobileBankingDTO,MobileBanking> {


    @Autowired
    public MobileBankingRestController(MobileBankingService mobileBankInterface){

        super(mobileBankInterface);
    }
}
