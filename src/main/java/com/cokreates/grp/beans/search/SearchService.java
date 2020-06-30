package com.cokreates.grp.beans.search;

import com.cokreates.core.Constant;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOffice;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.webclient.DataServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    DataServiceClient dataServiceClient;

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
