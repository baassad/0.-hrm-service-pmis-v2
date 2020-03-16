package com.cokreates.grp.beans.personal.file;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class FileService extends MasterService<FileDTO,File> {

    public FileService(RequestBuildingComponent<FileDTO> requestBuildingComponent,
                       DataServiceRestTemplateClient< FileDTO, File> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "file"));
    }
    
    @Override
    public Class getDtoClass() {
        return FileDTO.class;
    }
}
