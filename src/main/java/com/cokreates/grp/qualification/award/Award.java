package com.cokreates.grp.qualification.award;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Award extends BaseEntity {

    private String titleOfAward, awardReceivalPlace, awardReceivedDate;
    //tempData obj
}
