package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
public class TrainingRestController extends MasterRestController<TrainingDTO,Training> {

    @Autowired
    public TrainingRestController(TrainingService serviceInterface){
        super(serviceInterface);
    }

}
