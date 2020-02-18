package com.cokreates.grp.qualification.professionalCertification;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessionalCertificationDTO extends MasterDTO {

    private String title, institution, duration, country, comment;
    private Date receivalDate, expiryDate;

}
