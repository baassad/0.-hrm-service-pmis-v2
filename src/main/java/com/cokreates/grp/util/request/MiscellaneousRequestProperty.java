package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.Set;

@Data
public class MiscellaneousRequestProperty {
    private Set<String> employeeOidList;
    private Set<String> officeOidList;
}
