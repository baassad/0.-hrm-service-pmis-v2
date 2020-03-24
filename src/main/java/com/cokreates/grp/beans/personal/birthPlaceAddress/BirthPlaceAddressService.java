package com.cokreates.grp.beans.personal.birthPlaceAddress;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class BirthPlaceAddressService extends MasterService<BirthPlaceAddressDTO,BirthPlaceAddress> {

    public BirthPlaceAddressService(RequestBuildingComponent<BirthPlaceAddressDTO> requestBuildingComponent,
                                    DataServiceRestTemplateClient< BirthPlaceAddressDTO, BirthPlaceAddress> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "birthPlaceAddress"));
        this.setType("Node");
    }
    
    @Override
    public Class getDtoClass() {
        return BirthPlaceAddressDTO.class;
    }

    @Override
    public Class getEntityClass() {return BirthPlaceAddress.class;}
}
