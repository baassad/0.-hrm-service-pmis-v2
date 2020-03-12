package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professional")
public class ProfessionalCertificationRestController extends MasterRestController<ProfessionalCertificationDTO,ProfessionalCertification> {

    @Autowired
    public ProfessionalCertificationRestController(CklServiceInterface<ProfessionalCertificationDTO,ProfessionalCertification> serviceInterface){
        super(serviceInterface);
    }
}
