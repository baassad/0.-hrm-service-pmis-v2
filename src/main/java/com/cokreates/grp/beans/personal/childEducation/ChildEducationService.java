package com.cokreates.grp.beans.personal.childEducation;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChildEducationService extends MasterService<ChildEducationDTO,ChildEducation> {

    public ChildEducationService(RequestBuildingComponent<ChildEducationDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient<ChildEducationDTO, ChildEducation> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "childEducation"));
    }
    
    @Override
    public Class getDtoClass() {
        return ChildEducationDTO.class;
    }
}
