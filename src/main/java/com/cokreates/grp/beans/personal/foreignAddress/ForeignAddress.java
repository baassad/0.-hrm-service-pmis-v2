package com.cokreates.grp.beans.personal.foreignAddress;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForeignAddress extends BaseEntity {

    private String houseRoadBlockSector;
    private String stateProvinceRegion;
    private String postCode;
    private String city;
    private String country;
    private String phone;
    private String email;
    //tempData obj
}
