package com.cokreates.grp.beans.personal.emergencyContactAddress;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emergency-contact-address")
public class EmergencyContactAddressRestController extends MasterRestController<EmergencyContactAddressDTO,EmergencyContactAddress> {

    @Autowired
    public EmergencyContactAddressRestController(EmergencyContactAddressService emergencyContactAddressService){
        super(emergencyContactAddressService);
    }
}
