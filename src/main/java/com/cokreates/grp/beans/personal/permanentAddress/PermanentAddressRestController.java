package com.cokreates.grp.beans.personal.permanentAddress;

import com.cokreates.core.MasterRestController;
import com.cokreates.grp.beans.personal.permanentAddress.PermanentAddress;
import com.cokreates.grp.beans.personal.permanentAddress.PermanentAddressDTO;
import com.cokreates.grp.beans.personal.permanentAddress.PermanentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permanent-address")
public class PermanentAddressRestController extends MasterRestController<PermanentAddressDTO, PermanentAddress> {

    @Autowired
    public PermanentAddressRestController(PermanentAddressService permanentAddressService){
        super(permanentAddressService);
    }
}
