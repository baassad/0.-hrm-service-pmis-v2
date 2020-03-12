package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class AwardService extends MasterService<AwardDTO, Award> {
    public AwardService(RequestBuildingComponent<AwardDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
