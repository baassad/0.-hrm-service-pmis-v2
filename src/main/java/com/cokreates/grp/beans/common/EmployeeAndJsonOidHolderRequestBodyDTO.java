package com.cokreates.grp.beans.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeAndJsonOidHolderRequestBodyDTO {

    @NotNull
    private String employeeOid;

    @NotNull
    private String jsonRowOid;

}
