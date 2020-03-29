package com.cokreates.grp.beans.personal.birthPlaceAddress;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BirthPlaceAddressDTO extends MasterDTO {
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

}