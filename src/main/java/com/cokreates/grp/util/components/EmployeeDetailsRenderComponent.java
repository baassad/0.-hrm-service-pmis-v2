package com.cokreates.grp.util.components;


import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.RequestModel;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.employee.EmployeeCreationDTO;
import com.cokreates.grp.util.request.*;
import com.cokreates.grp.util.webclient.GlobalClient;
import com.cokreates.grp.util.webclient.HrmPimClient;
import com.cokreates.grp.util.webclient.OrganogramClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeDetailsRenderComponent {

    @Autowired
    GlobalClient globalClient;

    @Autowired
    HrmPimClient hrmPimClient;

    @Autowired
    OrganogramClient organogramClient;

    public EmployeeCreationDTO getEmployeeCreationDTO(RequestModel<OidRequestBodyDTO> requestDTO){

        EmployeeCreationDTO employeeCreationDTO = new EmployeeCreationDTO();

        ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> organogramRequest = getRequestForOrganogram(requestDTO);

        ResponseModel<EmployeeInformationDTO> organogramResponse = organogramClient.getEmployeeInformationFromOrganogram(organogramRequest);

        List<EmployeeInformationDTO> employeeInformationDTOS = organogramResponse.getBody().getData();

        int informationIndex = 0;

        /*for(EmployeeInformationDTO employeeInformationDTO : employeeInformationDTOS){

            if(employeeInformationDTO.getResponsibilityType().equalsIgnoreCase("Not Assigned")){
                employeeInformationDTOS.remove(employeeInformationDTO);
                break;
            }

        }*/

        if(employeeInformationDTOS.size() > 0) {
            EmployeeInformationDTO employeeInformationDTO = employeeInformationDTOS.get(informationIndex);

            BeanUtils.copyProperties(employeeInformationDTO, employeeCreationDTO);
            employeeCreationDTO.setPhone(employeeInformationDTO.getMobileNo());


            ServiceRequestDTO<OidRequestBodyDTO> hrmPimRequest = getRequestForHrmPim(requestDTO);
            ResponseModel<EmployeePersonalInfoDTO> employeePersonalInfoResponse = hrmPimClient.getEmployeePersonalInfoByEmployeeOid(hrmPimRequest);

            EmployeePersonalInfoDTO employeePersonalInfoDTO = employeePersonalInfoResponse.getBody().getData().get(0);
            BeanUtils.copyProperties(employeePersonalInfoDTO,employeeCreationDTO);
            employeeCreationDTO.setDateOfBirth(employeePersonalInfoDTO.getDateOfBirth());
            if(employeePersonalInfoDTO.getGender() != null){
                if(employeePersonalInfoDTO.getGender().equalsIgnoreCase("Male")){
                    employeeCreationDTO.setGender("পুরুষ");

                }else {
                    employeeCreationDTO.setGender("নারী");
                }

            }


            ServiceRequestDTO<NameRequestBodyDTO> cmnGlobalRequest = getRequestForCmnGlobal(requestDTO,employeePersonalInfoDTO.getMaritalStatus());

            ResponseModel<MaritalStatusDTO> maritalDTOResponse = globalClient.getMaritalStatusByName(cmnGlobalRequest);
            employeeCreationDTO.setMaritalStatus(maritalDTOResponse.getBody().getData().get(0).getNameBn());

        }

        return employeeCreationDTO;

    }

    public ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> getRequestForOrganogram(RequestModel<OidRequestBodyDTO> requestDTO){

        ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> request = new ServiceRequestDTO<>();

        OidSetWithStrictnessRequestBodyDTO body = new OidSetWithStrictnessRequestBodyDTO();
        List<String> oids = new ArrayList<>();
        oids.add(requestDTO.getBody().getData().get(0).getOid());
        body.setOids(oids);
        body.setStrict("No");

        request.setBody(body);
        request.setMeta(requestDTO.getMeta());
        request.setHeader(requestDTO.getHeader());

        return request;
    }

    public ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> getRequestForOrganogramOidSet(DataRequestHeaderModel headerModel,Set<String> oids){

        ServiceRequestDTO<OidSetWithStrictnessRequestBodyDTO> request = new ServiceRequestDTO<>();

        OidSetWithStrictnessRequestBodyDTO body = new OidSetWithStrictnessRequestBodyDTO();
        List<String> oidList = new ArrayList<>();
        oidList.addAll(oids);

        body.setOids(oidList);
        body.setStrict("No");

        request.setBody(body);
        request.setMeta(new HashMap<>());
        request.setHeader(headerModel);

        return request;
    }


    public ServiceRequestDTO<OidRequestBodyDTO> getRequestForHrmPim(RequestModel<OidRequestBodyDTO> requestModel){

        ServiceRequestDTO<OidRequestBodyDTO> request = new ServiceRequestDTO<>();

        request.setBody(requestModel.getBody().getData().get(0));
        request.setMeta(requestModel.getMeta());
        request.setHeader(requestModel.getHeader());

        return  request;

    }

    public ServiceRequestDTO<NameRequestBodyDTO> getRequestForCmnGlobal(RequestModel<OidRequestBodyDTO> requestModel,String name){

        NameRequestBodyDTO body = new NameRequestBodyDTO();
        body.setName(name);
        ServiceRequestDTO<NameRequestBodyDTO> requestDTO = new ServiceRequestDTO<>();
        requestDTO.setHeader(requestModel.getHeader());
        requestDTO.setMeta(requestModel.getMeta());
        requestDTO.setBody(body);

        return requestDTO;

    }

}
