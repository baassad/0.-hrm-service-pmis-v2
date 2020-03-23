package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Travel extends BaseEntity {

    private String nameEn;
    private String nameBn;
    private String purpose;
    private Date from;
    private Date to;
    private String type;
    private String govtOrderNo, govtOrderDate;
}
