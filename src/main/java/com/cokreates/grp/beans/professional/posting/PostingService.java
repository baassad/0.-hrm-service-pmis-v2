package com.cokreates.grp.beans.professional.posting;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class PostingService extends MasterService<PostingDTO, Posting> {
    public PostingService(RequestBuildingComponent<PostingDTO> requestBuildingComponent,
                          DataServiceRestTemplateClient< PostingDTO, Posting> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "posting"));
    }
    
    @Override
    public Class getDtoClass() {
        return PostingDTO.class;
    }
}
