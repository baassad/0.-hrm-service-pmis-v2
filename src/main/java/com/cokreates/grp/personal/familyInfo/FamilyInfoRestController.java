package com.cokreates.grp.personal.familyInfo;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family-info")
public class FamilyInfoRestController extends MasterRestController<FamilyInfoDTO,FamilyInfo> {

    @Autowired
    public FamilyInfoRestController(CklServiceInterface<FamilyInfoDTO,FamilyInfo> cklServiceInterface){
        super(cklServiceInterface);
    }
}
