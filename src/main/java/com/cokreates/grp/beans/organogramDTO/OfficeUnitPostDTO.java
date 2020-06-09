package com.cokreates.grp.beans.organogramDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
