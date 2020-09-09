package com.cokreates.grp.beans.personal.mobileBanking;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MobileBankingService extends MasterService<MobileBankingDTO,MobileBanking> {

    public MobileBankingService(RequestBuildingComponent<MobileBankingDTO> requestBuildingComponent,
                       DataServiceRestTemplateClient<MobileBankingDTO, MobileBanking> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "mobileBanking"));
        this.setType("List");
    }

    @Override
    public Class getDtoClass() {
        return MobileBankingDTO.class;
    }

    @Override
    public Class getEntityClass() {
        return MobileBanking.class;
    }
}
