package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.organogramDTO.OfficeDTO;
import com.cokreates.grp.beans.organogramDTO.OfficeUnitDTO;
import com.cokreates.grp.beans.organogramDTO.OfficeUnitPostDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO {

    @NotEmpty
    private List<OfficeDTO> offices;

    @NotEmpty
    private List<OfficeUnitDTO> officeUnits;

    @NotEmpty
    private List<OfficeUnitPostDTO> officeUnitPostS;

}
