package com.cokreates.grp.beans.personal.emergencyContact;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmergencyContactService extends MasterService<EmergencyContactDTO,EmergencyContact> {

    public EmergencyContactService(RequestBuildingComponent<EmergencyContactDTO> requestBuildingComponent,
                                   DataServiceRestTemplateClient< EmergencyContactDTO, EmergencyContact> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "emergencyContact"));
    }
    
    @Override
    public Class getDtoClass() {
        return EmergencyContactDTO.class;
    }
}
