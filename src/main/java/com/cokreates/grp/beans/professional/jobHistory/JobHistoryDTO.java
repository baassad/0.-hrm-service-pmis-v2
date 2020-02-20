package com.cokreates.grp.beans.professional.jobHistory;


import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobHistoryDTO extends MasterDTO {

    private String post;
    private String type;
    private Date from;
    private Date to;
}
