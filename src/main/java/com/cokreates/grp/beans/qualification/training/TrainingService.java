package com.cokreates.grp.beans.qualification.training;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class TrainingService extends MasterService<TrainingDTO, Training> {
    public TrainingService(RequestBuildingComponent<TrainingDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient< TrainingDTO, Training> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification","trainingAndProfessionalCertification", "training"));
    }
    
    @Override
    public Class getDtoClass() {
        return TrainingDTO.class;
    }
}
