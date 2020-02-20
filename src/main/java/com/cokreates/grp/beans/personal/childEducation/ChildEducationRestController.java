package com.cokreates.grp.beans.personal.childEducation;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/child-education")
public class ChildEducationRestController extends MasterRestController<ChildEducationDTO,ChildEducation> {
    @Autowired
    public ChildEducationRestController(CklServiceInterface<ChildEducationDTO,ChildEducation> childEducationServiceInterface){

        super(childEducationServiceInterface);
    }
}
