package com.cokreates.grp.beans.personal.health;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Health extends BaseEntity {

    private String height;
    private String identifyingMark;
    private String speciallyAbled;

    private String weight;
}
