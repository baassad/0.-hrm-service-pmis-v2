package com.cokreates.grp.beans.search;

import lombok.Data;

import java.util.Set;

@Data
public class OfficeWithGradeRequestBodyDTO extends EmployeeOfficeRelatedOidHolderRequestBodyDTO{

    private Set<String> listOfGradeOid;
}
