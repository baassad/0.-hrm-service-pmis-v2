package com.cokreates.grp.beans.professional.jobHistory;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobHistory extends BaseEntity {

    private String post, type, officeName, officeAddress;
    private Date from, to;
}
