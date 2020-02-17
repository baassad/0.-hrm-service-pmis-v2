package com.cokreates.grp.professional.serviceHistory;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceHistory extends BaseEntity {
    private String post, from, to, type;
}
