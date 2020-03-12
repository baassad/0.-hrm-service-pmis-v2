package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class PostingService extends MasterService<PostingDTO, Posting> {
    public PostingService(RequestBuildingComponent<PostingDTO> requestBuildingComponent,
                          DataServiceRestTemplateClient< PostingDTO, Posting> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}
