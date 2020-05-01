package com.cokreates.grp.beans.common;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDetailsMasterDTO extends MasterDTO {

    private GeneralDTO general;

    private List<EmployeeOfficeDTO> nodes;
}
