package com.cokreates.grp.beans.qualification.professionalQualification;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalQualification extends BaseEntity {

    private String qualification;
}