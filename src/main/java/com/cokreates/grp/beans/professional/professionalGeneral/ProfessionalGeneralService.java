package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class ProfessionalGeneralService extends MasterService<ProfessionalGeneralDTO, ProfessionalGeneral> {
    public ProfessionalGeneralService(RequestBuildingComponent<ProfessionalGeneralDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
