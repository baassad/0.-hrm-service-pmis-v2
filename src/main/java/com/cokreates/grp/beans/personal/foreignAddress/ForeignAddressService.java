package com.cokreates.grp.beans.personal.foreignAddress;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class ForeignAddressService extends MasterService<ForeignAddressDTO,ForeignAddress> {

    public ForeignAddressService(RequestBuildingComponent<ForeignAddressDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient< ForeignAddressDTO, ForeignAddress> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "foreignAddress"));
        this.setType("Node");
    }
    
    @Override
    public Class getDtoClass() {
        return ForeignAddressDTO.class;
    }

    @Override
    public Class getEntityClass() {return ForeignAddress.class;}
}
