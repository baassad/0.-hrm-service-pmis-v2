package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Training extends BaseEntity {

    private String subjectName, institution, country, grade, position, fundingSource;
    private Date stratedFrom, endedOn;
    private String[] subjectType;
}
