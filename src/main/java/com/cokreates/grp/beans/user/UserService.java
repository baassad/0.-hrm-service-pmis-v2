package com.cokreates.grp.beans.user;

import com.cokreates.core.Constant;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeInformationFromAuthDTO;
import com.cokreates.grp.beans.common.LoginInfoDTO;
import com.cokreates.grp.beans.common.RequesterCommentDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.util.webservice.WebService;
import com.netflix.discovery.converters.Auto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Data
@RequestScope
public class UserService {

    @Autowired
    EmployeeService employeeService;

    public LoginInfoDTO loggedInEmployee;

    @Autowired
    WebService webService;

    @Value("${auth-user.url}")
    private String userUrl;

    public RequesterCommentDTO getRequesterCommentFromLoginInfo() {

        RequesterCommentDTO requesterCommentDTO = new RequesterCommentDTO();
        requesterCommentDTO.setRequesterOid(loggedInEmployee.getOid());
        requesterCommentDTO.setMesssage("");
        requesterCommentDTO.setDateAndTime(new Date(System.currentTimeMillis()));

        List<EmployeeInformationDTO> profiles;
        GetListByOidSetRequestBodyDTO dto = new GetListByOidSetRequestBodyDTO();
        dto.setOids(Arrays.asList(loggedInEmployee.getOid()));
        profiles = employeeService.getEmployeeInformationDTO(dto);
        if(profiles ==null || profiles.isEmpty()==true) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("Employee office not found with given oid");
        }

        employeeService.setMissingData(profiles);
        profiles
                .stream()
                .filter(profile -> (twoStringMatched(profile.getEmployeeOfficeOid(), loggedInEmployee.getEmployeeOfficeOid())
                                &&  twoStringMatched(profile.getOfficeUnitOid(), loggedInEmployee.getOfficeUnitOid())
                                &&  twoStringMatched(profile.getOfficeUnitPostOid(), loggedInEmployee.getOfficeUnitPostOid())))
                .forEach(profile -> {
                    requesterCommentDTO.setNameEn(profile.getNameEn());
                    requesterCommentDTO.setNameBn(profile.getNameBn());

                    requesterCommentDTO.setPostnameEn(profile.getOfficeUnitPostNameEn());
                    requesterCommentDTO.setPostnameBn(profile.getOfficeUnitPostNameBn());
                });

        return requesterCommentDTO;
    }

    public boolean twoStringMatched(String one, String two) {
        if (one == null && two == null) return true;
        if (one == null ^ two == null) return false;
        return one.equals(two);
    }

    public List<EmployeeInformationFromAuthDTO> getUserOid(Set<String> oids) {

        List<EmployeeInformationFromAuthDTO> employeeInformationDTOS = new ArrayList<>();

        try {
            employeeInformationDTOS =
                    webService.getRestTemplateDataResponse(userUrl + Constant.AUTHENTICATION_V1_RESOURCE + Constant.GET_USERS_BY_EMPLOYEE, EmployeeInformationFromAuthDTO.class, oids);
        } catch (Exception e) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

        return employeeInformationDTOS;

    }

}
