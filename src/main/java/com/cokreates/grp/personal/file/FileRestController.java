package com.cokreates.grp.personal.file;

import com.cokreates.core.CklServiceInterface;
import com.cokreates.core.MasterRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileRestController extends MasterRestController<FileDTO,File> {

    @Autowired
    public FileRestController(CklServiceInterface<FileDTO,File> serviceInterface){
        super(serviceInterface);
    }
}
