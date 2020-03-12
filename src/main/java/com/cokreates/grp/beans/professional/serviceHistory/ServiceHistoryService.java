package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class ServiceHistoryService extends MasterService<ServiceHistoryDTO, ServiceHistory> {
    public ServiceHistoryService(RequestBuildingComponent<ServiceHistoryDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
