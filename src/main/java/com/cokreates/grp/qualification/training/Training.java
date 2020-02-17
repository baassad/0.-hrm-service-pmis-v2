package com.cokreates.grp.qualification.training;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Training extends BaseEntity {

    private String subjectName, subjectType, institution, country, grade, position;
    private Date stratedFrom, endedOn;
}
