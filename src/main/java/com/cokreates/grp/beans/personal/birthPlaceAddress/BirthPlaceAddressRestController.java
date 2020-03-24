package com.cokreates.grp.beans.personal.birthPlaceAddress;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birth-place-address")
public class BirthPlaceAddressRestController extends MasterRestController<BirthPlaceAddressDTO,BirthPlaceAddress> {

    @Autowired
    public BirthPlaceAddressRestController(BirthPlaceAddressService birthPlaceAddressService){
        super(birthPlaceAddressService);
    }
}
