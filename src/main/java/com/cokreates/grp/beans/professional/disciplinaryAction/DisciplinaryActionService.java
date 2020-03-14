package com.cokreates.grp.beans.professional.disciplinaryAction;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaryActionService extends MasterService<DisciplinaryActionDTO,DisciplinaryAction> {
    public DisciplinaryActionService(RequestBuildingComponent<DisciplinaryActionDTO> requestBuildingComponent,
                                     DataServiceRestTemplateClient< DisciplinaryActionDTO, DisciplinaryAction> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}