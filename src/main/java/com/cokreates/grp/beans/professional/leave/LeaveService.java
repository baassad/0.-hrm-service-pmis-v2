package com.cokreates.grp.beans.professional.leave;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class LeaveService extends MasterService<LeaveDTO, Leave> {
    public LeaveService(DataServiceClient<LeaveDTO> dataServiceClient, RequestBuildingComponent<LeaveDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
