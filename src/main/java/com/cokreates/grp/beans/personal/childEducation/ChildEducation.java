package com.cokreates.grp.beans.personal.childEducation;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChildEducation extends BaseEntity {

    private String institutionName;
    private String classes;
    private String subject;
    private String year;
    private String result;
    private String comment;
    //tempData obj
}
