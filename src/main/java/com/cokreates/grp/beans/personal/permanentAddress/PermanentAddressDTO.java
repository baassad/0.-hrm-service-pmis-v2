package com.cokreates.grp.beans.personal.permanentAddress;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermanentAddressDTO extends MasterDTO {
    private String road;
    private String house;
    private String village;
    private String postOffice;
    private String postCode;
    private String thana;
    private String district;
    private String division;
    private String country;
    private String phone;
    private String email;

}

//"road", "config", "createdOn", "nodeOid", "oid", "village", "postCode", 
//"district", "updatedBy", "temp", "country", "updatedOn", "postOffice", "createdBy", 
//"house", "division", "phone", "email", "thana", "addressType", "rowStatus", "dataStatus"