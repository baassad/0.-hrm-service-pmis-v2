package com.cokreates.grp.beans.professional.jobHistory;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class JobHistoryService extends MasterService<JobHistoryDTO, JobHistory> {
    public JobHistoryService(RequestBuildingComponent<JobHistoryDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
