package com.cokreates.grp.beans.user;

import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.LoginInfoDTO;
import com.cokreates.grp.beans.common.RequesterCommentDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.request.GetListByOidSetRequestBodyDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Data
public class UserService {

    @Autowired
    EmployeeService employeeService;

    public LoginInfoDTO loggedInEmployee;

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

}
