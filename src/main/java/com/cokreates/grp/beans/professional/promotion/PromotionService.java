package com.cokreates.grp.beans.professional.promotion;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class PromotionService extends MasterService<PromotionDTO, Promotion> {
    public PromotionService(RequestBuildingComponent<PromotionDTO> requestBuildingComponent,
                            DataServiceRestTemplateClient< PromotionDTO, Promotion> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "promotion"));
    }
    
    @Override
    public Class getDtoClass() {
        return PromotionDTO.class;
    }
}
