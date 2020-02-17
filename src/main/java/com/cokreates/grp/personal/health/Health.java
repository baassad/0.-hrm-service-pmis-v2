package com.cokreates.grp.personal.health;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Health extends BaseEntity {

    private String height;
    private String identifyingMark;
    private String speciallyAbled;

    private List<InjuriesSickness> injuriesSicknesses;

    //tempData obj
}
