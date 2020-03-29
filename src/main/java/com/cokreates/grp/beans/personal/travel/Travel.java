package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Travel extends BaseEntity {

    private String countryNameEn;
    private String countryNameBn;
    private String purpose;
    private Date from;
    private Date to;
    private String govtOrderNo, govtOrderDate;
}
