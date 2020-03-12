package com.cokreates.grp.beans.professional.leave;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import org.springframework.stereotype.Service;

@Service
public class LeaveService extends MasterService<LeaveDTO, Leave> {
    public LeaveService(RequestBuildingComponent<LeaveDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
