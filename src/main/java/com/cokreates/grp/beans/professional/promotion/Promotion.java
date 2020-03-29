package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Promotion extends BaseEntity {

    private String gono, natureOfPromotion, payScaleToWhichPromoted, rank;
    private Date dateOfPromotion, dateOfGO;
    //tempData obj
}
