package com.cokreates.grp.beans.personal.emergencyContactAddress;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmergencyContactAddress extends BaseEntity {

    private String houseRoadBlockSector;
    private String villageColonyWard;
    private String postOffice;
    private String postCode;
    private String thana;
    private String district;
    private String division;
    private String country;
    private String phone;
    private String email;
    //tempData obj
}
