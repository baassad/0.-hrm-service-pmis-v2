package com.cokreates.grp.beans.personal.emergencyContactAddress;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class EmergencyContactAddressService extends MasterService<EmergencyContactAddressDTO,EmergencyContactAddress> {

    public EmergencyContactAddressService(RequestBuildingComponent<EmergencyContactAddressDTO> requestBuildingComponent,
                                          DataServiceRestTemplateClient< EmergencyContactAddressDTO, EmergencyContactAddress> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "emergencyContactAddress"));
        this.setType("Node");
    }
    
    @Override
    public Class getDtoClass() {
        return EmergencyContactAddressDTO.class;
    }

    @Override
    public Class getEntityClass() {return EmergencyContactAddress.class;}
}
