package com.cokreates.grp.beans.personal.address;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressService extends MasterService<AddressDTO,Address> {

    public AddressService(RequestBuildingComponent<AddressDTO> requestBuildingComponent,
                          DataServiceRestTemplateClient< AddressDTO, Address> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "address"));
    }
    
    @Override
    public Class getDtoClass() {
        return AddressDTO.class;
    }
}
