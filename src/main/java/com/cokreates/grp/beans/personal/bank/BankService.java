package com.cokreates.grp.beans.personal.bank;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankService extends MasterService<BankDTO, Bank> {

    public BankService(RequestBuildingComponent<BankDTO> requestBuildingComponent,
                       DataServiceRestTemplateClient<BankDTO, Bank> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("personal", "bank"));
    }
    
    @Override
    public Class getDtoClass() {
        return BankDTO.class;
    }
}