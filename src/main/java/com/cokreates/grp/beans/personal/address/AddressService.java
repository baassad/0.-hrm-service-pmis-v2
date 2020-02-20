package com.cokreates.grp.beans.personal.address;

import com.cokreates.core.MasterRestController;
import com.cokreates.core.MasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService extends MasterService<AddressDTO,Address> {

    @Autowired
    public AddressService(){

    }
}
