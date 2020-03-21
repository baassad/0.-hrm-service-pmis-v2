package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EducationService extends MasterService<EducationDTO, Education> {
    public EducationService(RequestBuildingComponent<EducationDTO> requestBuildingComponent,
                            DataServiceRestTemplateClient< EducationDTO, Education> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "education"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return EducationDTO.class;
    }
}
