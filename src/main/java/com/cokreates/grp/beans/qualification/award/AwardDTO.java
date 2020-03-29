package com.cokreates.grp.beans.qualification.award;

import java.sql.Date;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AwardDTO extends MasterDTO {

    private String titleOfAward, awardReceivalPlace, country;
    private Date awardReceivedDate;

}
