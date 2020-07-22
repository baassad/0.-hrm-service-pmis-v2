package com.cokreates.grp.beans.search;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class EmployeeOfficeRelatedOidHolderRequestBodyDTO {

    @NotNull
    Set<String> listOfOfficeOid;

    @NotNull
    Set<String> listOfOfficeUnitOid;

    @NotNull
    Set<String> listOfOfficeUnitPostOid;

    Set<String> listOfPostOid;

    @NotNull
    Integer limit;

    @NotNull
    Integer offset;
}
