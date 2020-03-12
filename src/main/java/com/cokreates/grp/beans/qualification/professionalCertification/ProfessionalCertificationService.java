package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class ProfessionalCertificationService extends MasterService<ProfessionalCertificationDTO, ProfessionalCertification> {
    public ProfessionalCertificationService(DataServiceClient<ProfessionalCertificationDTO> dataServiceClient, RequestBuildingComponent<ProfessionalCertificationDTO> requestBuildingComponent){
        super(dataServiceClient,requestBuildingComponent);
    }
}
