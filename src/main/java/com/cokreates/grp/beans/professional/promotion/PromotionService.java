package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class PromotionService extends MasterService<PromotionDTO, Promotion> {
    public PromotionService(DataServiceClient<PromotionDTO> dataServiceClient, RequestBuildingComponent<PromotionDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
