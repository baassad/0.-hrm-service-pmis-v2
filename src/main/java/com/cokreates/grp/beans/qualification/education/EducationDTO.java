package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationDTO extends MasterDTO {

    private String degree;
    private String subject;
    private String institution;
    private String isForeignInstitution;
    private String result;
    private String achieved;
    private String scale;
    private String startingDate;
    private String endingDate;
    private String country;

}
