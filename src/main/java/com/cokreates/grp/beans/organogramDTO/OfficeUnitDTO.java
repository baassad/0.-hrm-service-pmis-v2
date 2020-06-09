package com.cokreates.grp.beans.organogramDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeUnitDTO {

    private String oid;
    private String nameEn;
    private String nameBn;
    private BigDecimal displayOrder;
    private BigDecimal businessOrder;
    private String parentOid;
    private String officeUnitCategoryOid;
    private String officeOid;
    private String ministryOid;

}
