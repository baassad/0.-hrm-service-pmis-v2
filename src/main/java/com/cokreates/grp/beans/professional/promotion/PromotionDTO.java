package com.cokreates.grp.beans.professional.promotion;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PromotionDTO extends MasterDTO {

    private String preDesignation, postDesignation, GONo, natureOfPromotion, payScaleToWhichPromoted, rank;
    private Date dateOfPromotion, dateOfGO;
}
