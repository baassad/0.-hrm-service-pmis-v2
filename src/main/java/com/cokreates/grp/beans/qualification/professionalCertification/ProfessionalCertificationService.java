package com.cokreates.grp.beans.qualification.professionalCertification;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class ProfessionalCertificationService extends MasterService<ProfessionalCertificationDTO, ProfessionalCertification> {
    public ProfessionalCertificationService(RequestBuildingComponent<ProfessionalCertificationDTO> requestBuildingComponent,
                                            DataServiceRestTemplateClient< ProfessionalCertificationDTO, ProfessionalCertification> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "trainingAndProfessionalCertification","professionalCertification"));
    }
    
    @Override
    public Class getDtoClass() {
        return ProfessionalCertificationDTO.class;
    }
}
