package com.cokreates.grp.beans.qualification.education;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Education extends BaseEntity {

    private String degree, subject, institution, isForeignInstitution, result, achieved, scale, country;
    private Date startingDate, endingDate;
    //tempData obj
}
