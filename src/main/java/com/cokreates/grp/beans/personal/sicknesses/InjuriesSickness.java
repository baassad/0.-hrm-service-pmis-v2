package com.cokreates.grp.beans.personal.sicknesses;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class InjuriesSickness extends BaseEntity {

    private String description;
    private String type;
    private Date from;
    private Date to;
    private String comment;
}
