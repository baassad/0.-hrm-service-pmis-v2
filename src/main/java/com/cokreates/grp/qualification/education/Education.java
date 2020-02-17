package com.cokreates.grp.qualification.education;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Education extends BaseEntity {

    private String degree, subject, institution, isForeignInstitution, major, result, achieved, scale, startingDate,
            endingDate;
    //tempData obj
}
