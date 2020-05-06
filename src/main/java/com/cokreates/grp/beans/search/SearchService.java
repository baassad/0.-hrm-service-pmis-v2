package com.cokreates.grp.beans.search;

import com.cokreates.core.Constant;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.webclient.DataServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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




        switch(request.getSearchProcedure()) {

            case 1 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitPostOid(), Constant.OFFICE_UNIT_POST,request.getName());
                     break;

            case 2 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitOid(),Constant.OFFICE_UNIT,request.getName());
                     break;

            case 3 : searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeOid(),Constant.OFFICE,request.getName());
                     break;

            case 5 : {
                     if(request.getListOfOfficeUnitPostOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitPostOid(), Constant.OFFICE_UNIT_POST,request.getName());
                     }else if (request.getListOfOfficeUnitOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeUnitOid(),Constant.OFFICE_UNIT,request.getName());
                     }else if (request.getListOfOfficeOid().size() > 0){
                         searchDTO = getEmployeeSearchDTO(searchDTO,request.getListOfOfficeOid(),Constant.OFFICE,request.getName());
                     }
                     break;

            }

            default: break;

        }

        HashMap<String,Object> map = new HashMap<>();
        //map.put(Constant.COUNT, employeeOffices.getTotalElements());
        ServiceRequestDTO<DataServiceEmployeeSearchDTO> dataServiceRequest = new ServiceRequestDTO<>();
        dataServiceRequest.setBody(searchDTO);
        ResponseModel<EmployeeInformationDTO> response = dataServiceClient.getEmployees(dataServiceRequest);

        List<EmployeeInformationDTO> employeeInformationDTOS = response.getBody().getData();

        return employeeInformationDTOS;




    }




}
