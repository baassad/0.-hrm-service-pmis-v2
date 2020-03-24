package com.cokreates.grp.beans.personal.presentAddress;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class PresentAddressService extends MasterService<PresentAddressDTO,PresentAddress> {

    public PresentAddressService(RequestBuildingComponent<PresentAddressDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient< PresentAddressDTO, PresentAddress> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "presentAddress"));
        this.setType("Node");
    }
    
    @Override
    public Class getDtoClass() {
        return PresentAddressDTO.class;
    }

    @Override
    public Class getEntityClass() {return PresentAddress.class;}
}
