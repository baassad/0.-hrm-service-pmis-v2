package com.cokreates.grp.beans.personal.foreignAddress;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForeignAddressDTO extends MasterDTO {
    private String houseRoadBlockSector;
    private String stateProvinceRegion;
    private String postCode;
    private String city;
    private String country;
    private String phone;
    private String email;

}

//"road", "config", "createdOn", "nodeOid", "oid", "village", "postCode", 
//"district", "updatedBy", "temp", "country", "updatedOn", "postOffice", "createdBy", 
//"house", "division", "phone", "email", "thana", "addressType", "rowStatus", "dataStatus"