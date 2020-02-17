package com.cokreates.grp.professional.serviceHistory;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceHistory extends BaseEntity {
    private String post, type;
    private Date from, to;
}
