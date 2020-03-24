package com.cokreates.grp.beans.personal.foreignAddress;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foreign-address")
public class ForeignAddressRestController extends MasterRestController<ForeignAddressDTO,ForeignAddress> {

    @Autowired
    public ForeignAddressRestController(ForeignAddressService foreignAddressService){
        super(foreignAddressService);
    }
}
