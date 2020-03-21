package com.cokreates.grp.beans.qualification.professionalQualification;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProfessionalQualificationService extends MasterService<ProfessionalQualificationDTO, ProfessionalQualification> {
    public ProfessionalQualificationService(RequestBuildingComponent<ProfessionalQualificationDTO> requestBuildingComponent,
                                            DataServiceRestTemplateClient< ProfessionalQualificationDTO, ProfessionalQualification> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification","professionalQualification"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return ProfessionalQualificationDTO.class;
    }

    @Override
    public Class getEntityClass() {return ProfessionalQualification.class;}
}
