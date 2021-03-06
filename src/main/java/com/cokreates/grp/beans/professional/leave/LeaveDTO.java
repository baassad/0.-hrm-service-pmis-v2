package com.cokreates.grp.beans.professional.leave;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveDTO extends MasterDTO {


    private String type, reason, duration;
    private Date from, to;

}
