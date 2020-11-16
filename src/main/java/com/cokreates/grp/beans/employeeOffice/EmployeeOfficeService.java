package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2DTO;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2Service;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.daas.DataServiceResponseForList;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeOfficeService extends MasterService<EmployeeOfficeDTO,EmployeeOffice> {

    @Autowired
    EmployeeOfficeService employeeOfficeService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DataServiceClient dataServiceClient;
    
    @Autowired
    EmployeeOfficeV2Service employeeOfficeV2Service;

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

    public EmployeeOfficeDTO updateEmployeeOffice(EmployeeOfficeDTO dto,String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOfficeForUpdate(dto,employeeOid);

        EmployeeOfficeV2 node = employeeOfficeV2Service.findByPmisOidAndEmployeeOfficeOidAndRowStatus(employeeOid, dto.getOid());
        if (node == null) {
			node = new EmployeeOfficeV2();
		}
        
        node.setEmployeeOid(employeeOid);
        node.setEmployeeOfficeOid(dto.getOid());
        node.setIsApprover(dto.getIsApprover());
        node.setIsReviewer(dto.getIsReviewer());
        node.setIsOfficeAdmin(dto.getIsOfficeAdmin());
        node.setIsAttendanceAdmin(dto.getIsAttendanceAdmin());
        node.setIsAttendanceDataEntryOperator(dto.getIsAttendanceDataEntryOperator());
        node.setIsAwardAdmin(dto.getIsAwardAdmin());
        
        EmployeeOfficeV2DTO nodeDTO = getModelMapper().map(node, EmployeeOfficeV2DTO.class);
        List<EmployeeOfficeV2DTO> nodes = new ArrayList<EmployeeOfficeV2DTO>();
        nodes.add(nodeDTO);
        
        employeeOfficeV2Service.create(nodes);
        
        DataServiceResponse<EmployeeOfficeDTO> response = dataServiceClient.updateEmployeeOffice(request);

        return response.getBody().getMain();

    }

    public List<EmployeeOfficeDTO> getEmployeeOfficeList(String employeeOid, String officeUnitPostOid){

        DataServiceRequest<EmployeeDTO> request = employeeService.getRequestBuildingComponent().getRequestForRead(null,null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        DataServiceResponseForList<EmployeeOfficeDTO> response = dataServiceClient.getEmployeeOfficeList(request);

        List<EmployeeOfficeDTO> employeeOfficeDTOList = response.getBody().getMain();

        List<EmployeeOfficeDTO> finalEmployeeOfficeDTOList = new ArrayList<>();

        for(EmployeeOfficeDTO employeeOfficeDTO : employeeOfficeDTOList){
            if(employeeOfficeDTO.getOfficeUnitPostOid() != null && employeeOfficeDTO.getStatus().equalsIgnoreCase("Active")
                    && employeeOfficeDTO.getOfficeUnitPostOid().equalsIgnoreCase(officeUnitPostOid)){
                finalEmployeeOfficeDTOList.add(employeeOfficeDTO);
            }

        }

        return finalEmployeeOfficeDTOList;

    }


    @Override
    public Class getDtoClass() {
        return EmployeeOfficeDTO.class;
    }

    @Override
    public Class getEntityClass() {return EmployeeOffice.class;}

}
