package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailsDTO {

    private GeneralDTO general;

    private List<EmployeeOfficeDTO> nodes;
}
