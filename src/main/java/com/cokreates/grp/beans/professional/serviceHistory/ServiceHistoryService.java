package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ServiceHistoryService extends MasterService<ServiceHistoryDTO, ServiceHistory> {
    public ServiceHistoryService(RequestBuildingComponent<ServiceHistoryDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient< ServiceHistoryDTO, ServiceHistory> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "serviceHistory"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return ServiceHistoryDTO.class;
    }
}
