package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TravelService extends MasterService<TravelDTO,Travel> {

    public TravelService(RequestBuildingComponent<TravelDTO> requestBuildingComponent,
                         DataServiceRestTemplateClient< TravelDTO, Travel> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
