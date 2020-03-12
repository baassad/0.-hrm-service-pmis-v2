package com.cokreates.grp.beans.personal.address;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressRestController extends MasterRestController<AddressDTO,Address> {

    @Autowired
    public AddressRestController(AddressService addressInterface){
        super(addressInterface);
    }
}
