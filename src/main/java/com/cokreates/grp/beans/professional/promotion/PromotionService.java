package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import org.springframework.stereotype.Service;

@Service
public class PromotionService extends MasterService<PromotionDTO, Promotion> {
    public PromotionService(RequestBuildingComponent<PromotionDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
