package com.cokreates.grp.beans.personal.general;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneralService extends MasterService<GeneralDTO,General> {

    public GeneralService(DataServiceClient<GeneralDTO> dataServiceClient, RequestBuildingComponent<GeneralDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);

    }
}
