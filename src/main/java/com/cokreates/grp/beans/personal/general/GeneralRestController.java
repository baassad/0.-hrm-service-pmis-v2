package com.cokreates.grp.beans.personal.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cokreates.core.MasterRestController;

@RestController
@RequestMapping("/general")
public class GeneralRestController extends MasterRestController<GeneralDTO,General> {

    public GeneralRestController(GeneralService serviceInterface){
        super(serviceInterface);
    }

}