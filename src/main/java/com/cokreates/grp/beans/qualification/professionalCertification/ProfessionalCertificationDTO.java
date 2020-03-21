package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessionalCertificationDTO extends MasterDTO {

    private String title, institution, duration, country,comment;
    private String receivalDate, expiryDate;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
