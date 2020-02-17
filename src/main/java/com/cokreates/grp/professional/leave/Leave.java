package com.cokreates.grp.professional.leave;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Leave extends BaseEntity {

    private String type, reason;
    private Date from, to;
}
