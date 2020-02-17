package com.cokreates.grp.professional.jobHistory;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobHistory extends BaseEntity {

    private String post, from, to, type;
}
