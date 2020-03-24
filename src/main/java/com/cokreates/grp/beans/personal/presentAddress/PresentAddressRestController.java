package com.cokreates.grp.beans.personal.presentAddress;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/present-address")
public class PresentAddressRestController extends MasterRestController<PresentAddressDTO,PresentAddress> {

    @Autowired
    public PresentAddressRestController(PresentAddressService presentAddressService){
        super(presentAddressService);
    }
}
