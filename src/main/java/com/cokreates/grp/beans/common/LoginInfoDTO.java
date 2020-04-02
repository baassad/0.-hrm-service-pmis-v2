package com.cokreates.grp.beans.common;

import com.cokreates.core.MasterDTO;
import lombok.Data;

@Data
public class LoginInfoDTO extends MasterDTO {

    private String employeeOfficeOid;

    private String officeOid;

    private String employeeOid;

    private String officeUnitPostOid;

    private String userOid;

    private String officeUnitOid;
}
