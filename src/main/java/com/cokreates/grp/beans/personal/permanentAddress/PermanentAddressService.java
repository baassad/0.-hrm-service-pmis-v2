package com.cokreates.grp.beans.personal.permanentAddress;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class PermanentAddressService extends MasterService<PermanentAddressDTO,PermanentAddress> {

    public PermanentAddressService(RequestBuildingComponent<PermanentAddressDTO> requestBuildingComponent,
                                   DataServiceRestTemplateClient< PermanentAddressDTO, PermanentAddress> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "permanentAddress"));
        this.setType("Node");
    }
    
    @Override
    public Class getDtoClass() {
        return PermanentAddressDTO.class;
    }

    @Override
    public Class getEntityClass() {return PermanentAddress.class;}
}
