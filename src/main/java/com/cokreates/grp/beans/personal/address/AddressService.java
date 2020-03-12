package com.cokreates.grp.beans.personal.address;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService extends MasterService<AddressDTO,Address> {

    public AddressService(RequestBuildingComponent<AddressDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
