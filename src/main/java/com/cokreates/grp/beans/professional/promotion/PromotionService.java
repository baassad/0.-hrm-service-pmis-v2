package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PromotionService extends MasterService<PromotionDTO, Promotion> {
    public PromotionService(RequestBuildingComponent<PromotionDTO> requestBuildingComponent,
                            DataServiceRestTemplateClient< PromotionDTO, Promotion> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "promotion"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return PromotionDTO.class;
    }

    @Override
    public Class getEntityClass() {return Promotion.class;}
}
