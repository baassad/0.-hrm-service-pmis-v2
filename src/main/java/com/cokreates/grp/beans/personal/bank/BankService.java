package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.personal.address.Address;
import com.cokreates.grp.beans.personal.address.AddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BankService extends MasterService<BankDTO, Bank> {

    @Autowired
    public BankService(){

    }
}