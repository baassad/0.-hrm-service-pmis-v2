package com.cokreates.grp.beans.professional.professionalGeneral;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

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
