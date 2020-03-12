package com.cokreates.grp.beans.professional.leave;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class LeaveService extends MasterService<LeaveDTO, Leave> {
    public LeaveService(RequestBuildingComponent<LeaveDTO> requestBuildingComponent,
                        DataServiceRestTemplateClient< LeaveDTO, Leave> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
