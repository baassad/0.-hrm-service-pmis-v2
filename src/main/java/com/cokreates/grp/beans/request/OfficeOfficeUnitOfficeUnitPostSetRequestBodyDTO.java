package com.cokreates.grp.beans.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO {

    @NotEmpty
    private List<String> officeOids;

    @NotEmpty
    private List<String> officeUnitOids;

    @NotEmpty
    private List<String> officeUnitPostOids;

}
