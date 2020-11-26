package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.Constant;
import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.MasterService;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeInformationIncludedGradeDTO;
import com.cokreates.grp.beans.common.GradeDTO;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.pim.pmis.EmployeeOfficeDetails;
import com.cokreates.grp.beans.pim.pmis.EmployeePersonalDetails;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.daas.DataServiceResponseForList;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.cokreates.grp.util.webservice.WebService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeOfficeService extends MasterService<EmployeeOfficeDTO,EmployeeOffice> {

    @Value("${hrm-service.url}")
    String hrmServicePimUrl;

    @Autowired
    RequestBuildingComponent requestBuildingComponent;

    @Autowired
    PmisRepository pmisRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    WebService webService;

    @Autowired
    ObjectMapper objectMapper;

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

    public EmployeeOfficeDTO updateEmployeeOffice(EmployeeOfficeDTO dto,String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOfficeForUpdate(dto,employeeOid);

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

    public Map<String,GradeDTO> getGradesMap(DataRequestHeaderModel header){

        GetListByOidSetRequestBodyDTO requestBodyDTO = new GetListByOidSetRequestBodyDTO();
        requestBodyDTO.setOids(new ArrayList<>());
        requestBodyDTO.setStrict("No");

        List<GradeDTO> grades = new ArrayList<>();

        try {
            grades = webService.postForList(hrmServicePimUrl + "/grade/v1/get-list", GradeDTO.class, requestBuildingComponent.getTheServiceRequestDTO(header, requestBodyDTO));
        }catch (RuntimeException e){

        }

        Map<String,GradeDTO> gradeMap = new HashMap<>();
        Map<String,GradeDTO> gradeOidMap = new HashMap<>();

        for(GradeDTO gradeDTO:grades){
            gradeMap.put(gradeDTO.getNameBn(),gradeDTO);
        }

        return gradeMap;

    }



    public List<EmployeeInformationIncludedGradeDTO> getEmployeesByOidSet(ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO){

        GetListByOidSetRequestBodyDTO requestBody = requestDTO.getBody();

        List<String> employeeOidList = requestBody.getOids();

        List<EmployeeOfficeDetails> employeeOfficeDetailsList = pmisRepository.getEmployeeOffices(employeeOidList);
        List<EmployeePersonalDetails> employeePersonalDetailsList = pmisRepository.getEmployeePersonalInfoDetails(employeeOidList);

        List<EmployeeInformationIncludedGradeDTO> employeeInformationDTOList = new ArrayList<>();

        Map<String,String> oidEmployeeOfficeMap = new HashMap<>();
        Map<String,EmployeePersonalDetails> oidPersonalDetailsMap = new HashMap<>();

        Map<String,GradeDTO> gradeMap = getGradesMap(requestDTO.getHeader());

        for(EmployeeOfficeDetails employeeOfficeDetails:employeeOfficeDetailsList){
            oidEmployeeOfficeMap.put(employeeOfficeDetails.getOid(),employeeOfficeDetails.getNodes());
        }



        for(EmployeePersonalDetails employeePersonalDetails:employeePersonalDetailsList){
            //oidPersonalDetailsMap.put(employeePersonalDetails.getOid(),employeePersonalDetails);
            List<EmployeeOfficeDTO> employeeOfficeList = new ArrayList<>();
            try {
                employeeOfficeList = objectMapper.readValue(oidEmployeeOfficeMap.get(employeePersonalDetails.getOid()), new TypeReference<List<EmployeeOfficeDTO>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            for(EmployeeOfficeDTO employeeOfficeDetails:employeeOfficeList){
                EmployeeInformationIncludedGradeDTO employeeInformationDTO = new EmployeeInformationIncludedGradeDTO();
                BeanUtils.copyProperties(employeeOfficeDetails,employeeInformationDTO);
                BeanUtils.copyProperties(employeePersonalDetails,employeeInformationDTO);
                employeeInformationDTO.setEmployeeOfficeOid(employeeOfficeDetails.getOid());
                employeeInformationDTO.setGrade(gradeMap.get(employeePersonalDetails.getGrade()));
                employeeInformationDTOList.add(employeeInformationDTO);
            }

        }
        return employeeInformationDTOList;
    }

    public String formatOidSetForQuery(List<String> oids){

        String start = "%%\"oid\" *: *(" ;

        String concatenatedString = "\"" + oids.get(0) + "\"";
        for (int i = 1; i < oids.size(); i++) {
            concatenatedString += "|\"" + oids.get(i) + "\"";
        }

        String end = ")%%";

        return start + concatenatedString + end;
    }

    public List<EmployeeInformationIncludedGradeDTO> getEmployeesByEmployeeOfficeOidSet(ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO){

        GetListByOidSetRequestBodyDTO requestBody = requestDTO.getBody();
        String concatenatedString = formatOidSetForQuery(requestBody.getOids());

        System.out.println("Concatenated String " + concatenatedString);

        List<EmployeeOfficeDetails> employeeOfficeDetailsList = pmisRepository.getEmployeeOfficeDetails(concatenatedString);

        Map<String,String> oidEmployeeOfficeMap = new HashMap<>();
        Map<String,EmployeePersonalDetails> oidPersonalDetailsMap = new HashMap<>();

        Map<String,GradeDTO> gradeMap = getGradesMap(requestDTO.getHeader());

        List<String> employeeOidList = new ArrayList<>();

        for(EmployeeOfficeDetails employeeOfficeDetails:employeeOfficeDetailsList){
            employeeOidList.add(employeeOfficeDetails.getOid());
            oidEmployeeOfficeMap.put(employeeOfficeDetails.getOid(),employeeOfficeDetails.getNodes());
        }

        List<EmployeePersonalDetails> employeePersonalDetailsList = pmisRepository.getEmployeePersonalInfoDetails(employeeOidList);
        List<EmployeeInformationIncludedGradeDTO> employeeInformationDTOList = new ArrayList<>();

        for(EmployeePersonalDetails employeePersonalDetails:employeePersonalDetailsList){
            //oidPersonalDetailsMap.put(employeePersonalDetails.getOid(),employeePersonalDetails);
            List<EmployeeOfficeDTO> employeeOfficeList = new ArrayList<>();
            try {
                employeeOfficeList = objectMapper.readValue(oidEmployeeOfficeMap.get(employeePersonalDetails.getOid()), new TypeReference<List<EmployeeOfficeDTO>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            for(EmployeeOfficeDTO employeeOfficeDetails:employeeOfficeList){
                if(!requestBody.getOids().contains(employeeOfficeDetails.getOid())){
                   continue;
                }
                EmployeeInformationIncludedGradeDTO employeeInformationDTO = new EmployeeInformationIncludedGradeDTO();
                BeanUtils.copyProperties(employeeOfficeDetails,employeeInformationDTO);
                BeanUtils.copyProperties(employeePersonalDetails,employeeInformationDTO);
                employeeInformationDTO.setGrade(gradeMap.get(employeePersonalDetails.getGrade()));
                employeeInformationDTO.setEmployeeOfficeOid(employeeOfficeDetails.getOid());
                employeeInformationDTOList.add(employeeInformationDTO);
            }

        }

        return employeeInformationDTOList;

    }



    @Override
    public Class getDtoClass() {
        return EmployeeOfficeDTO.class;
    }

    @Override
    public Class getEntityClass() {return EmployeeOffice.class;}

}
