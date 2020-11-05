package com.cokreates.grp.beans.search;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OfficeWithGradeRequestBodyDTO extends EmployeeOfficeRelatedOidHolderRequestBodyDTO{

    private List<String> listOfGradeOid;
}
