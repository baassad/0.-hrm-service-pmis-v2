package com.cokreates.grp.beans.qualification.education;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class EducationService extends MasterService<EducationDTO, Education> {
    public EducationService(RequestBuildingComponent<EducationDTO> requestBuildingComponent,
                            DataServiceRestTemplateClient< EducationDTO, Education> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "education"));
    }
    
    @Override
    public Class getDtoClass() {
        return EducationDTO.class;
    }
}
