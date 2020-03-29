package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Award extends BaseEntity {

    private String titleOfAward, awardReceivalPlace, country;
    private Date awardReceivedDate;

}
