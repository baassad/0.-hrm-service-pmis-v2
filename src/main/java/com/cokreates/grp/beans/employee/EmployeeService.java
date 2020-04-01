package com.cokreates.grp.beans.employee;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterDTO;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.common.*;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.file.FileDTO;
import com.cokreates.grp.beans.personal.file.FileService;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.personal.general.GeneralService;
import com.cokreates.grp.beans.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.daas.DataServiceResponseForList;
import com.cokreates.grp.util.components.ClassConversionComponent;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.cokreates.grp.util.webservice.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService extends MasterService<EmployeeDTO, Employee> {

    @Value("${cmn-service-file-management.url}")
    private String fileServiceUrl;

    @Value("${cmn-service-organogram.url}")
    private String organogramUrl;

    @Autowired
    DataServiceClient dataServiceClient;

    @Autowired
    ClassConversionComponent conversionComponent;

    @Autowired
    GeneralService generalService;

    @Autowired
    WebService webService;

    @Autowired
    FileService fileService;

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

    public  List<EmployeeOfficeDTO> getEmployeeOfficeList(String employeeOid,String officeUnitPostOid){

        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForRead(null,null, employeeOid,
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

    private byte[] fetchPhoto(String employeeOid) {
        List<FileDTO> fileDTOs = fileService.getList(employeeOid);
        List<FileDTO> photos = fileDTOs.stream().filter(x -> "photo".equalsIgnoreCase(x.getFileName().trim())).collect(Collectors.toList());
        if(photos.isEmpty()) {
            return null;
        }
        MasterDTO dto = new MasterDTO();
        dto.setOid(photos.get(0).getFileOid());
        try {
            return webService.postForByteArray(fileServiceUrl+ Constant.ENDPOINT_DOWNLOAD_FILE, dto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public EmployeeOfficeDTO appendEmployeeOfficeDTO(EmployeeOfficeDTO employeeOfficeDTO,String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOffice(employeeOfficeDTO,employeeOid);

        DataServiceResponse<EmployeeOfficeDTO> response = dataServiceClient.appendEmployeeOffice(request);

        return response.getBody().getMain();

    }

    public EmployeeDTO importEmployee(EmployeeCreationDTO dto){
        DataServiceRequest<EmployeeCreationDTO> request = getRequestBuildingComponent().getRequestForImport(dto,dto.getOid());

        DataServiceResponse<EmployeeDTO> response = dataServiceClient.importEmployee(request);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setOid(response.getBody().getOid());
        return employeeDTO;
    }


    public List<EmployeeInformationDTO> getProfileInfo(String employeeOid) {
        List<EmployeeInformationDTO> profiles;
        GetListByOidSetRequestBodyDTO dto = new GetListByOidSetRequestBodyDTO();
        dto.setOids(Arrays.asList(employeeOid));
        dto.setStrict("No");
        profiles = webService.postForList(organogramUrl + Constant.ENDPOINT_GET_EMPLOYEE_PROFILE_INFO, EmployeeInformationDTO.class, dto);
        if(profiles!=null && profiles.isEmpty()==false) {
            for(EmployeeInformationDTO p : profiles) {
                p.setPhoto(fetchPhoto(employeeOid));
            }
        }
        return profiles;
    }


    @Override
    public Class getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public Class getEntityClass() {return Employee.class;}

}
