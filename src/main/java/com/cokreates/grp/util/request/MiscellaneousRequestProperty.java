package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MiscellaneousRequestProperty {
    private List<String> employeeOidList;
    private List<String> officeOidList;
    private List<String> officeUnitOidList;
}
