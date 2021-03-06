package com.cokreates.grp.beans.personal.health;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HealthDTO extends MasterDTO {


    private String height;
    private String identifyingMark;
    private String speciallyAbled;
    private String weight;

}
