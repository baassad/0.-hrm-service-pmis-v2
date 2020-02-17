package com.cokreates.grp.personal.health;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InjuriesSickness extends BaseEntity {

    private String description;
    private String type;
    private String from;
    private String to;
    private String comment;
}
