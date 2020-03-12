package com.cokreates.grp.beans.personal.emergencyContact;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emergency-contact")
public class EmergencyContactRestController extends MasterRestController<EmergencyContactDTO,EmergencyContact> {

    @Autowired
    public EmergencyContactRestController(EmergencyContactService emergencyContactServiceInterface){
        super(emergencyContactServiceInterface);
    }

}
