package com.cokreates.grp.beans.organogramDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfficeUnitPostDTO {

    private String oid;
    private BigDecimal displayOrder;
    private String postOid;
    private PostDTO post;
    private String phoneNo;
    private BigDecimal businessOrder;
    private String parentOid;
    private String officeUnitOid;
    
    private String officeOid;

}
