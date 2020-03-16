package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProfessionalCertificationDTO extends MasterDTO {

    private String title, institution, duration, country,comment;
    private Date receivalDate, expiryDate;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
