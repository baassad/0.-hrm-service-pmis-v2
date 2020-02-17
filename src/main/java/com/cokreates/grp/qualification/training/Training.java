package com.cokreates.grp.qualification.training;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Training extends BaseEntity {

    private String subjectName, subjectType, institution, country, stratedFrom, endedOn, grade, position;
}
