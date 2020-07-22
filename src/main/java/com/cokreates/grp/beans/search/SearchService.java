package com.cokreates.grp.beans.search;

import com.cokreates.core.Constant;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.OfficeUnitAndPostOidHolderRequestBodyDTO;
import com.cokreates.grp.beans.common.OfficeUnitPostDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOffice;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOfficeRepository;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.EmployeeDetailsRenderComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.OrganogramClient;
import com.cokreates.grp.util.webservice.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Value("${cmn-service-organogram.url}")
    String cmnOrganogramUrl;

    @Autowired
    EmployeeDetailsRenderComponent employeeDetailsRenderComponent;

    @Autowired
    EmployeeOfficeRepository employeeOfficeRepository;

    @Autowired
    PmisRepository pmisRepository;

    @Autowired
    DataServiceClient dataServiceClient;

    @Autowired
    OrganogramClient organogramClient;

    @Autowired
    WebService webService;

    public DataServiceEmployeeSearchDTO getEmployeeSearchDTO(DataServiceEmployeeSearchDTO searchDTO, Set<String> oidList, String category, String name){
        searchDTO.setListOfOid(oidList);
        searchDTO.setCategory(category);
        searchDTO.setName(name);

        return searchDTO;
    }


    public List<EmployeeInformationDTO> getTheEmployees(ServiceRequestDTO<NamedEmployeeRequestBodyDTO> requestDTO){

        NamedEmployeeRequestBodyDTO request = requestDTO.getBody();

        DataServiceEmployeeSearchDTO searchDTO = new DataServiceEmployeeSearchDTO();

        searchDTO.setLimit(request.getLimit());
        searchDTO.setOffset(request.getOffset());

        System.out.println("Request search Procedure " + request.getSearchProcedure() + "..........................................");

        String filterCriterion  = "";
        Set<String> intendedOids = new HashSet<>();


        switch(request.getSearchProcedure()) {

            case 1 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitPostOid(), Constant.OFFICE_UNIT_POST,request.getName());
                     filterCriterion = Constant.OFFICE_UNIT_POST;
                     intendedOids = request.getListOfOfficeUnitPostOid();
                     break;

            case 2 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitOid(),Constant.OFFICE_UNIT,request.getName());
                      filterCriterion = Constant.OFFICE_UNIT;
                      intendedOids = request.getListOfOfficeUnitOid();
                      break;

            case 3 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeOid(),Constant.OFFICE,request.getName());
                      filterCriterion = Constant.OFFICE;
                      intendedOids = request.getListOfOfficeOid();
                      break;

            case 5 : {
                     if(request.getListOfOfficeUnitPostOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitPostOid(), Constant.OFFICE_UNIT_POST,request.getName());
                         filterCriterion = Constant.OFFICE_UNIT_POST;
                         intendedOids = request.getListOfOfficeUnitPostOid();
                     }else if (request.getListOfOfficeUnitOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitOid(),Constant.OFFICE_UNIT,request.getName());
                         filterCriterion = Constant.OFFICE_UNIT;
                         intendedOids = request.getListOfOfficeUnitOid();
                     }else if (request.getListOfOfficeOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeOid(),Constant.OFFICE,request.getName());
                         filterCriterion = Constant.OFFICE;
                         intendedOids = request.getListOfOfficeOid();
                     }
                     break;

            }

            default: break;

        }

        HashMap<String,Object> map = new HashMap<>();
        //map.put(Constant.COUNT, employeeOffices.getTotalElements());
        ServiceRequestDTO<DataServiceEmployeeSearchDTO> dataServiceRequest = new ServiceRequestDTO<>();
        dataServiceRequest.setBody(searchDTO);
        ResponseModel<EmployeeDetails> response = dataServiceClient.getEmployees(dataServiceRequest);

        List<EmployeeDetails> employeeDetailsList = response.getBody().getData();

        List<EmployeeInformationDTO> employeeInformationDTOS = convertEmployeeDetailsToEmployeeInformationDTO(employeeDetailsList,filterCriterion,intendedOids);

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> getTheEmployeesNotImported(ServiceRequestDTO<NamedEmployeeRequestBodyDTO> requestDTO){

        NamedEmployeeRequestBodyDTO request = requestDTO.getBody();

        List<EmployeeOffice> employeeOffices = new ArrayList<>();
        Set<String> employeeOids = new HashSet<>();


        if(request.getListOfPostOid() != null && request.getListOfOfficeUnitOid() != null && request.getListOfPostOid().size() > 0 && request.getListOfOfficeUnitOid().size() > 0){
            OfficeUnitAndPostOidHolderRequestBodyDTO requestBodyDTO = new OfficeUnitAndPostOidHolderRequestBodyDTO();
            requestBodyDTO.setOfficeUnitOid(request.getListOfOfficeUnitOid().iterator().next());
            requestBodyDTO.setPostOid(request.getListOfPostOid().iterator().next());

            ServiceRequestDTO<OfficeUnitAndPostOidHolderRequestBodyDTO> serviceRequestDTO = new ServiceRequestDTO<>();
            serviceRequestDTO.setHeader(requestDTO.getHeader());
            serviceRequestDTO.setMeta(new HashMap<>());
            serviceRequestDTO.setBody(requestBodyDTO);

            ResponseModel<OfficeUnitPostDTO> officeUnitPostDTO = organogramClient.getOfficeUnitPostByOfficeUnitAndPostFromOrganogram(serviceRequestDTO);
            if(officeUnitPostDTO.getBody().getData().size() > 0){
                Set<String> officeUnitPostOids = new HashSet<>();
                officeUnitPostOids.add(officeUnitPostDTO.getBody().getData().get(0).getOid());
                employeeOffices = employeeOfficeRepository.findByOfficeUnitPostOidInAndStatusAndIsDeleted(officeUnitPostOids,"Active","No");
            }else {
                return new ArrayList<>();
            }


        }else if(request.getListOfOfficeUnitPostOid() != null && request.getListOfOfficeUnitPostOid().size() > 0){
            employeeOffices = employeeOfficeRepository.findByOfficeUnitPostOidInAndStatusAndIsDeleted(request.getListOfOfficeUnitPostOid(),"Active","No");

        }else if (request.getListOfOfficeUnitOid() != null && request.getListOfOfficeUnitOid().size() > 0){
            employeeOffices = employeeOfficeRepository.findByOfficeUnitOidInAndStatusAndIsDeleted(request.getListOfOfficeUnitOid(),"Active","No");

        }else if (request.getListOfOfficeOid() != null && request.getListOfOfficeOid().size() > 0){
            employeeOffices = employeeOfficeRepository.findByOfficeOidInAndStatusAndIsDeleted(request.getListOfOfficeOid(),"Active","No");
        }


        for(EmployeeOffice employeeOffice:employeeOffices){
            System.out.println("Employee Oid : " + employeeOffice.getEmployeeMasterInfo().getOid());
            employeeOids.add(employeeOffice.getEmployeeMasterInfo().getOid());
        }

        Set<String> pmisOids = pmisRepository.getAllOidsFromPmis();

        employeeOids.removeAll(pmisOids);

        System.out.println("Set :" +  employeeOids);

        if(employeeOids.size() == 0){
            return new ArrayList<>();
        }


        return webService.postForList(cmnOrganogramUrl + "/search/v1/get-list-by-oid-set",EmployeeInformationDTO.class,employeeDetailsRenderComponent.getRequestForOrganogramOidSet(requestDTO.getHeader(),employeeOids));

    }

    public List<EmployeeInformationDTO> convertEmployeeDetailsToEmployeeInformationDTO(List<EmployeeDetails> employeeDetailList,String criterion,Set<String> oidSet){

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        for(EmployeeDetails employeeDetail : employeeDetailList){

            for(EmployeeOfficeDTO employeeOffice:employeeDetail.getEmployee_office().getNodes()){

                if((criterion.equalsIgnoreCase(Constant.OFFICE) && !(oidSet.contains(employeeOffice.getOfficeOid()))) ||
                        (criterion.equalsIgnoreCase(Constant.OFFICE_UNIT) && !(oidSet.contains(employeeOffice.getOfficeUnitOid()))) ||
                            (criterion.equalsIgnoreCase(Constant.OFFICE_UNIT_POST) && !(oidSet.contains(employeeOffice.getOfficeUnitPostOid())))){
                    continue;
                }

                EmployeeInformationDTO employeeInformationDTO = new EmployeeInformationDTO();
                employeeInformationDTO.setOid(employeeDetail.getOid());
                employeeInformationDTO.setNameEn(employeeDetail.getPersonal_general().getNameEn());
                employeeInformationDTO.setNameBn(employeeDetail.getPersonal_general().getNameBn());
                employeeInformationDTO.setEmployeeOfficeOid(employeeOffice.getOid());
                employeeInformationDTO.setOfficeOid(employeeOffice.getOfficeOid());
                employeeInformationDTO.setOfficeUnitOid(employeeOffice.getOfficeUnitOid());
                employeeInformationDTO.setOfficeUnitPostOid(employeeOffice.getOfficeUnitPostOid());
                employeeInformationDTO.setEmail(employeeDetail.getPersonal_general().getEmail());
                employeeInformationDTO.setMobileNo(employeeDetail.getPersonal_general().getPhone());
                employeeInformationDTO.setEmployeeTypeOid(employeeOffice.getEmploymentTypeOid());
                employeeInformationDTO.setIsAttendanceAdmin(employeeOffice.getIsAttendanceAdmin());
                employeeInformationDTO.setIsAttendanceDataEntryOperator(employeeOffice.getIsAttendanceDataEntryOperator());
                employeeInformationDTO.setResponsibilityType(employeeOffice.getResponsibilityType());
                employeeInformationDTOS.add(employeeInformationDTO);


            }

        }

        return employeeInformationDTOS;

    }




}
