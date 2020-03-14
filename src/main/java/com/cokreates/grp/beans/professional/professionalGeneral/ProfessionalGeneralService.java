package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProfessionalGeneralService extends MasterService<ProfessionalGeneralDTO, ProfessionalGeneral> {
    public ProfessionalGeneralService(RequestBuildingComponent<ProfessionalGeneralDTO> requestBuildingComponent,
                                      DataServiceRestTemplateClient< ProfessionalGeneralDTO, ProfessionalGeneral> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "general"));
    }
    
    @Override
    public Class getDtoClass() {
        return ProfessionalGeneralDTO.class;
    }
}
