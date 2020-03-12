package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professional-general")
public class ProfessionalGeneralRestController extends MasterRestController<ProfessionalGeneralDTO,ProfessionalGeneral> {

    @Autowired
    public ProfessionalGeneralRestController(ProfessionalGeneralService serviceInterface){
        super(serviceInterface);
    }

}
