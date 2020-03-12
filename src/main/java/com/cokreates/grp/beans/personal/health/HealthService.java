package com.cokreates.grp.beans.personal.health;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthService extends MasterService<HealthDTO,Health> {
    public HealthService(RequestBuildingComponent<HealthDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }

}
