package com.cokreates.grp.beans.personal.file;

import com.cokreates.core.MasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileService extends MasterService<FileDTO,File> {

    public FileService(){}
}
