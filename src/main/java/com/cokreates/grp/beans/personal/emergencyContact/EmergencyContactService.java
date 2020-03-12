package com.cokreates.grp.beans.personal.emergencyContact;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmergencyContactService extends MasterService<EmergencyContactDTO,EmergencyContact> {

    public EmergencyContactService(DataServiceClient<EmergencyContactDTO> dataServiceClient, RequestBuildingComponent<EmergencyContactDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
