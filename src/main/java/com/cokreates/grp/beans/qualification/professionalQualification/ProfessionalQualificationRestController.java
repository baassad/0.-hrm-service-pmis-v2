package com.cokreates.grp.beans.qualification.professionalQualification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cokreates.core.MasterRestController;

@RestController
@RequestMapping("/professional-qualification")
public class ProfessionalQualificationRestController extends MasterRestController<ProfessionalQualificationDTO,ProfessionalQualification
> {

    @Autowired
    public ProfessionalQualificationRestController(ProfessionalQualificationService serviceInterface){
        super(serviceInterface);
    }
}
