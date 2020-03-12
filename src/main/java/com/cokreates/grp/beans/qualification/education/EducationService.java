package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class EducationService extends MasterService<EducationDTO, Education> {
    public EducationService(DataServiceClient<EducationDTO> dataServiceClient, RequestBuildingComponent<EducationDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
