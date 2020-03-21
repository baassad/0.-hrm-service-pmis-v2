package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PostingService extends MasterService<PostingDTO, Posting> {
    public PostingService(RequestBuildingComponent<PostingDTO> requestBuildingComponent,
                          DataServiceRestTemplateClient< PostingDTO, Posting> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "posting"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return PostingDTO.class;
    }

    @Override
    public Class getEntityClass() {return Posting.class;}
}
