package com.cokreates.grp.beans.qualification.professionalQualification;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professional-qualification")
public class ProfessionalQualificationRestController extends MasterRestController<ProfessionalQualificationDTO,ProfessionalQualification
> {

    @Autowired
    public ProfessionalQualificationRestController(ProfessionalQualificationService serviceInterface){
        super(serviceInterface);
    }
}
