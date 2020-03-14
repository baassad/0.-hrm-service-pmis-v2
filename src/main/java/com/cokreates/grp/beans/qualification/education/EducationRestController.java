package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
public class EducationRestController extends MasterRestController<EducationDTO,Education> {

    @Autowired
    public EducationRestController(EducationService serviceInterface){
        super(serviceInterface);
    }
}
