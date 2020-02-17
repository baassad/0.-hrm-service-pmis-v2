package com.cokreates.grp.personal.travel;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Travel extends BaseEntity {

    private String nameEn;
    private String nameBn;
    private String purpose;
    private String from;
    private String to;
    private String type;
    //tempData obj
}
