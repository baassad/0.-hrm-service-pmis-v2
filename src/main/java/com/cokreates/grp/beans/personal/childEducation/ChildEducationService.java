package com.cokreates.grp.beans.personal.childEducation;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChildEducationService extends MasterService<ChildEducationDTO,ChildEducation> {

    public ChildEducationService(DataServiceClient<ChildEducationDTO> dataServiceClient, RequestBuildingComponent<ChildEducationDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
