package com.cokreates.grp.personal.childEducation;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChildEducation extends BaseEntity {

    private String institutionName, classes, subject, year, result, comment;
    //tempData obj
}
