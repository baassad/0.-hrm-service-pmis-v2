package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalCertificationService extends MasterService<ProfessionalCertificationDTO, ProfessionalCertification> {
    public ProfessionalCertificationService(RequestBuildingComponent<ProfessionalCertificationDTO> requestBuildingComponent,
                                            DataServiceRestTemplateClient< ProfessionalCertificationDTO, ProfessionalCertification> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }
}