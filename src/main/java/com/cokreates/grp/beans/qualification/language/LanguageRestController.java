package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageRestController extends MasterRestController<LanguageDTO,Language> {
    @Autowired
    public LanguageRestController(LanguageService cklServiceInterface){
        super(cklServiceInterface);
    }
}
