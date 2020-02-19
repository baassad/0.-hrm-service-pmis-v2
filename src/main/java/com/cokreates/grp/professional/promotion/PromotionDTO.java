package com.cokreates.grp.professional.promotion;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PromotionDTO extends MasterDTO {

    private String preDesignation, postDesignation, GONo, natureOfPromotion, payScaleToWhichPromoted;
    private Date dateOfPromotion, dateOfGO;
}
