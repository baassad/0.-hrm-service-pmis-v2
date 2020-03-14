package com.cokreates.grp.beans.professional.leave;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LeaveService extends MasterService<LeaveDTO, Leave> {
    public LeaveService(RequestBuildingComponent<LeaveDTO> requestBuildingComponent,
                        DataServiceRestTemplateClient< LeaveDTO, Leave> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "leave"));
    }
    
    @Override
    public Class getDtoClass() {
        return LeaveDTO.class;
    }
}
