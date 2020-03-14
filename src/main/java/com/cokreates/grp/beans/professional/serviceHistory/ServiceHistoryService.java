package com.cokreates.grp.beans.professional.serviceHistory;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class ServiceHistoryService extends MasterService<ServiceHistoryDTO, ServiceHistory> {
    public ServiceHistoryService(RequestBuildingComponent<ServiceHistoryDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient< ServiceHistoryDTO, ServiceHistory> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "serviceHistory"));
    }
    
    @Override
    public Class getDtoClass() {
        return ServiceHistoryDTO.class;
    }
}
