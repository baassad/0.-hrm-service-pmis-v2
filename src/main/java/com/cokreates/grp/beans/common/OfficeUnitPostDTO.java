package com.cokreates.grp.beans.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeUnitPostDTO {

    private String oid;

    private BigDecimal displayOrder;

    private String postOid;

    private String phoneNo;

    private String parentOid;

    private String officeUnitOid;

    private String officeOid;
}
