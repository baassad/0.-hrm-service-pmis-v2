package com.cokreates.grp.beans.personal.familyInfo;

import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family-info")
public class FamilyInfoRestController extends MasterRestController<FamilyInfoDTO,FamilyInfo> {

    @Autowired
    public FamilyInfoRestController(FamilyInfoService cklServiceInterface){
        super(cklServiceInterface);
    }
}
