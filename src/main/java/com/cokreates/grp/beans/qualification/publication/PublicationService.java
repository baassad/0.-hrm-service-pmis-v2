package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class PublicationService extends MasterService<PublicationDTO, Publication> {
    public PublicationService(DataServiceClient<PublicationDTO> dataServiceClient, RequestBuildingComponent<PublicationDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
