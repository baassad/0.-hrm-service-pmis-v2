package com.cokreates.grp.beans.professional.professionalGeneral;

import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.pim.pmis.EmployeeGovtId;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProfessionalGeneralService extends MasterService<ProfessionalGeneralDTO, ProfessionalGeneral> {


    @Autowired
    PmisRepository pmisRepository;

    public ProfessionalGeneralService(RequestBuildingComponent<ProfessionalGeneralDTO> requestBuildingComponent,
                                      DataServiceRestTemplateClient< ProfessionalGeneralDTO, ProfessionalGeneral> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("professional", "professionalGeneral"));
        this.setType("Node");
    }

    public List<EmployeeGovtId> getGovtIdByEmployeeOid(List<String> employeeOids){

        List<EmployeeGovtId> employeeGovtIds = pmisRepository.findGovtIdByEmployeeOid(employeeOids);

        return employeeGovtIds;
    }



    
    @Override
    public Class getDtoClass() {
        return ProfessionalGeneralDTO.class;
    }

    @Override
    public Class getEntityClass() {return ProfessionalGeneral.class;}
}
