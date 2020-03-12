package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class TrainingService extends MasterService<TrainingDTO, Training> {
    public TrainingService(DataServiceClient<TrainingDTO> dataServiceClient, RequestBuildingComponent<TrainingDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
