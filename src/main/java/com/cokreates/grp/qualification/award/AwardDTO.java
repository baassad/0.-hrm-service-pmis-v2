package com.cokreates.grp.qualification.award;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AwardDTO extends MasterDTO {

    private String titleOfAward;
    private String awardReceivalPlace;
    private Date awardReceivedDate;
}
