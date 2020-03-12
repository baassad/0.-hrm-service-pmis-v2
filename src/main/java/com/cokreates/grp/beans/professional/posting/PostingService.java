package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class PostingService extends MasterService<PostingDTO, Posting> {
    public PostingService(DataServiceClient<PostingDTO> dataServiceClient, RequestBuildingComponent<PostingDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
