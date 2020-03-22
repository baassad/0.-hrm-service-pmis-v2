package com.cokreates.grp.beans.employee;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.request.ActorRequestBodyDTO;
import com.cokreates.grp.util.request.ApprovalHistoryRequestBodyDTO;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService extends MasterService<EmployeeDTO, Employee> {

    public EmployeeService(RequestBuildingComponent<EmployeeDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient<EmployeeDTO, Employee> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public EmployeeDTO create(GeneralDTO dto) {
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestToCreateEmployee(dto, getDtoClass());

        String gDataEndPointUrl = getGData() + Constant.GDATA_CREATE + Constant.VERSION_1 + Constant.ENDPOINT_EMPLOYEE;

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request,gDataEndPointUrl);
    }

    @Override
    public EmployeeDTO getNode(String employeeOid) {
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(),null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_EMP;
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request, gDataEndPointUrl);

    }


    @Override
    public Class getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public Class getEntityClass() {return Employee.class;}

}
