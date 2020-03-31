package com.cokreates.grp.beans.employee;

import com.cokreates.core.Constant;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.common.EmployeeDetailsDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.personal.general.GeneralService;
import com.cokreates.grp.beans.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.ClassConversionComponent;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService extends MasterService<EmployeeDTO, Employee> {

    @Autowired
    DataServiceClient dataServiceClient;

    @Autowired
    ClassConversionComponent conversionComponent;

    @Autowired
    GeneralService generalService;

    public EmployeeService(RequestBuildingComponent<EmployeeDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient<EmployeeDTO, Employee> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public EmployeeDTO create(GeneralDTO dto) {
        dto = generalService.parseBeforeUpdate(dto);
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestToCreateEmployee(dto, getDtoClass());

        String gDataEndPointUrl = getGData() + Constant.GDATA_CREATE + Constant.VERSION_1 + Constant.ENDPOINT_EMPLOYEE;

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request,gDataEndPointUrl);
    }

    @Override
    public EmployeeDTO getNode(String employeeOid) {
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(),null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = getGData() + Constant.GDATA_GET + Constant.VERSION_1 + Constant.GDATA_EMP;
        log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request, gDataEndPointUrl);

    }

    public List<EmployeeInformationDTO> getEmployeeInformationDTO(GetListByOidSetRequestBodyDTO requestDTO){

        String employeeOid = requestDTO.getOids().get(0);

        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForGettingEmployeeDetails(employeeOid);

        String gDataEndPointUrl = getGData() + Constant.GDATA_GET + Constant.VERSION_1 + Constant.EMPLOYEE_DETAILS;
        log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);

        DataServiceResponse<EmployeeDetailsDTO> response = dataServiceClient.getEmployeeDetailsFromDaas(request);

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsToEmpInfo(response.getBody().getMain());

        for(EmployeeInformationDTO dto:employeeInformationDTOS){

            dto.setOid(employeeOid);
        }

        return employeeInformationDTOS;

    }

    public EmployeeOfficeDTO appendEmployeeOfficeDTO(EmployeeOfficeDTO employeeOfficeDTO,String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOffice(employeeOfficeDTO,employeeOid);

        DataServiceResponse<EmployeeOfficeDTO> response = dataServiceClient.appendEmployeeOffice(request);

        return response.getBody().getMain();

    }


    @Override
    public Class getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public Class getEntityClass() {return Employee.class;}

}
