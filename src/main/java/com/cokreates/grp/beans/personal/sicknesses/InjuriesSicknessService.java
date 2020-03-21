package com.cokreates.grp.beans.personal.sicknesses;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InjuriesSicknessService extends MasterService<InjuriesSicknessDTO,InjuriesSickness> {
    public InjuriesSicknessService(RequestBuildingComponent<InjuriesSicknessDTO> requestBuildingComponent,
                                   DataServiceRestTemplateClient< InjuriesSicknessDTO, InjuriesSickness> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "injuriesSickness"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return InjuriesSicknessDTO.class;
    }
}
