package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class TrainingService extends MasterService<TrainingDTO, Training> {
    public TrainingService(RequestBuildingComponent<TrainingDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient< TrainingDTO, Training> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
