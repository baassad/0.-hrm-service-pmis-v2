package com.cokreates.grp.professional.leave;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveDTO extends MasterDTO {


    private String type, reason;
    private Date from, to;


}
