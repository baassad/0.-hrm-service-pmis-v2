package com.cokreates.grp.professional.promotion;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Promotion extends BaseEntity {

    private String dateOfPromotion, preDesignation, postDesignation, dateOfGO, GONo, natureOfPromotion, payScaleToWhichPromoted;
    //tempData obj
}
