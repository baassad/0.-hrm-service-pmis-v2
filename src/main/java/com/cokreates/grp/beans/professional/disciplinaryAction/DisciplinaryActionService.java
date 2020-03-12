package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class DisciplinaryActionService extends MasterService<DisciplinaryActionDTO,DisciplinaryAction> {
    public DisciplinaryActionService(DataServiceClient<DisciplinaryActionDTO> dataServiceClient, RequestBuildingComponent<DisciplinaryActionDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
