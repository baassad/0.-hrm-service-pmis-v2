package com.cokreates.grp.beans.qualification.award;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class AwardService extends MasterService<AwardDTO, Award> {
    public AwardService(RequestBuildingComponent<AwardDTO> requestBuildingComponent,
                        DataServiceRestTemplateClient< AwardDTO, Award> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "award"));
    }
    
    @Override
    public Class getDtoClass() {
        return AwardDTO.class;
    }
}
