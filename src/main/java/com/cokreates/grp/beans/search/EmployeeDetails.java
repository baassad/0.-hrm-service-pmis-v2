package com.cokreates.grp.beans.search;

import com.cokreates.grp.beans.personal.general.General;
import lombok.Data;

@Data
public class EmployeeDetails {

    String oid;

    General personal_general;

    EmployeeOfficeInfo employee_office;

}
