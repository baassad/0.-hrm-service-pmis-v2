package com.cokreates.grp.config;


import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.LoginInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@Aspect
@Component
@Slf4j
public class ServiceMethodInterceptor {

    @Autowired
    HttpServletRequest request;



    @Around("execution(* com.cokreates.core.*Service.create(..))" +
            "execution(* com.cokreates.core.*Service.append(..))" +
            "execution(* com.cokreates.core.*Service.update(..))" +
            "execution(* com.cokreates.core.*Service.delete(..))")
    public void interceptCreateCalls(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LoginInfoDTO info = new LoginInfoDTO();
        getLoginInfoFromToken(info);
        String employeeOfficeOid = info.getEmployeeOfficeOid();
        String employeeOid = info.getEmployeeOid();
        String officeOid = info.getOfficeOid();
        System.out.println("EmployeeOid: " + employeeOid);
        System.out.println("OfficeOid: " + officeOid);
        System.out.println("EmployeeOfficeOid: " + employeeOfficeOid);
        Object[] args = proceedingJoinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof MasterDTO) {
                MasterDTO dto = (MasterDTO) args[i];
                dto.setCreatedBy(employeeOfficeOid);     //This record should be kept as it is. for employee with specific post.
                dto.setCreatedOn(new Timestamp(System.currentTimeMillis()));
                proceedingJoinPoint.proceed();;
            }
        }
    }


    private void getLoginInfoFromToken(LoginInfoDTO info) throws IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = bearerToken.replace("Bearer ", "");
        Jwt jwtToken = JwtHelper.decode(token.toString());    //Decode jwt token
        String claims = jwtToken.getClaims();
        JsonNode node = new ObjectMapper().readTree(claims);
        info.setEmployeeOfficeOid(node.get("employeeOfficeId").toString().replace("\"",""));
        info.setOfficeOid(node.get("officeId").toString().replace("\"",""));
        info.setEmployeeOid(node.get("employeeId").toString().replace("\"",""));
        info.setOfficeUnitPostOid(node.get("officeUnitPostId").toString().replace("\"",""));
        info.setUserOid(node.get("userOid").toString().replace("\"",""));
        info.setOfficeUnitOid(node.get("officeUnitId").toString().replace("\"",""));
    }

}
