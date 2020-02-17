package com.cokreates.grp.qualification.professionalCertification;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalCertification extends BaseEntity {

    private String title, institution, receivalDate, expiryDate, duration, country, comment;
}
