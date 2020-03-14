package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class PublicationService extends MasterService<PublicationDTO, Publication> {
    public PublicationService(RequestBuildingComponent<PublicationDTO> requestBuildingComponent,
                              DataServiceRestTemplateClient< PublicationDTO, Publication> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}