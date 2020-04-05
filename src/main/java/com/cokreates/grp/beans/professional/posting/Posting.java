package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Posting extends BaseEntity {

    private String post, organization, location, payScale, payLastDrawn, district, city, grade;
    private Date from, to;
}
