package com.cokreates.grp.beans.qualification.professionalQualification;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessionalQualificationDTO extends MasterDTO {

    private String qualification;

}
