package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posting")
public class PostingRestController extends MasterRestController<PostingDTO,Posting> {

    @Autowired
    public PostingRestController(CklServiceInterface<PostingDTO,Posting> serviceInterface){
        super(serviceInterface);
    }
}
