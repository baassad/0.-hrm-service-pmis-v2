package com.cokreates.grp.util.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO {

    @NotNull
    private List<String> officeOids;

    @NotNull
    private List<String> officeUnitOids;

    @NotNull
    private List<String> officeUnitPostOids;

}
