package com.cokreates.grp.beans.qualification.professionalCertification;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalCertification extends BaseEntity {

    private String title, institution, duration, country, comment;
    private Date receivalDate, expiryDate;
}
