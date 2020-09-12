package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.personal.file.FileDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInformationFromAuthDTO {

    public String userOid;

    private String employeeOid;

}
