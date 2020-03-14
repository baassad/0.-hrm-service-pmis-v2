package com.cokreates.grp.beans.personal.travel;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TravelService extends MasterService<TravelDTO,Travel> {

    public TravelService(RequestBuildingComponent<TravelDTO> requestBuildingComponent,
                         DataServiceRestTemplateClient< TravelDTO, Travel> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "travel"));
    }
    
    @Override
    public Class getDtoClass() {
        return TravelDTO.class;
    }
}
