package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController extends MasterRestController<PromotionDTO,Promotion> {
    @Autowired
    public PromotionRestController(PromotionService serviceInterface){
        super(serviceInterface);
    }
}
