package com.cokreates.grp.beans.professional.jobHistory;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class JobHistoryService extends MasterService<JobHistoryDTO, JobHistory> {
    public JobHistoryService(RequestBuildingComponent<JobHistoryDTO> requestBuildingComponent,
                             DataServiceRestTemplateClient<JobHistoryDTO, JobHistory> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
