package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeOfficeService extends MasterService<EmployeeOfficeDTO,EmployeeOffice> {

    @Autowired
    EmployeeOfficeService employeeOfficeService;

    @Autowired
    DataServiceClient dataServiceClient;

    public EmployeeOfficeService(RequestBuildingComponent<EmployeeOfficeDTO> requestBuildingComponent,
                                 DataServiceRestTemplateClient< EmployeeOfficeDTO, EmployeeOffice> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public EmployeeOfficeDTO create(EmployeeOfficeDTO dto,String employeeOid) {
        dto = this.parseBeforeUpdate(dto);
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOffice(dto,employeeOid);

        DataServiceResponse<EmployeeOfficeDTO> response = dataServiceClient.appendEmployeeOffice(request);

        return response.getBody().getMain();
    }

    @Override
    public Class getDtoClass() {
        return EmployeeOfficeDTO.class;
    }

    @Override
    public Class getEntityClass() {return EmployeeOffice.class;}

}
