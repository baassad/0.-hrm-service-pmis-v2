package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class AwardService extends MasterService<AwardDTO, Award> {
    public AwardService(RequestBuildingComponent<AwardDTO> requestBuildingComponent,
                        DataServiceRestTemplateClient< AwardDTO, Award> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
