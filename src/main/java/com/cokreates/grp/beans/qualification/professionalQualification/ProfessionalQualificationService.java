package com.cokreates.grp.beans.qualification.professionalQualification;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class ProfessionalQualificationService extends MasterService<ProfessionalQualificationDTO, ProfessionalQualification> {
    public ProfessionalQualificationService(RequestBuildingComponent<ProfessionalQualificationDTO> requestBuildingComponent,
                                            DataServiceRestTemplateClient< ProfessionalQualificationDTO, ProfessionalQualification> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification","professionalQualification"));
    }
    
    @Override
    public Class getDtoClass() {
        return ProfessionalQualificationDTO.class;
    }
}
