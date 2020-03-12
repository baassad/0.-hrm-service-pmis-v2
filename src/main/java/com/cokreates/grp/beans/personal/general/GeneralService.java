package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class GeneralService extends MasterService<GeneralDTO,General> {

    public GeneralService(RequestBuildingComponent<GeneralDTO> requestBuildingComponent,
                          DataServiceRestTemplateClient< GeneralDTO, General> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "general"));
    }
}
