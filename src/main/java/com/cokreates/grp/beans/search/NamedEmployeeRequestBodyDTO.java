package com.cokreates.grp.beans.search;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class NamedEmployeeRequestBodyDTO extends EmployeeOfficeRelatedOidHolderRequestBodyDTO {


    @NotNull
    private String name;

    private Set<String> setOfFoundOfficeOid;

    private Set<String> setOfFoundOfficeUnitOid;

    private Set<String> setOfFoundOfficeUnitPostOid;

    private int searchProcedure;
}
