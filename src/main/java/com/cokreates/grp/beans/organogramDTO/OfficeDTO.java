package com.cokreates.grp.beans.organogramDTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OfficeDTO  {

    private String oid;
    private String nameEn;
    private String nameBn;
    private String status;
    private BigDecimal sortOrder;
    private String parentOid;
    private String rootOfficeOid;
    private String ministryOid;
    private String officeLayerOid;
    private String stakeholderOid;
    private String isRootOffice;
    
    private OfficeLayerDTO officeLayer;

    private String codeEn;
    private String codeBn;
    private String addressEn;
    private String addressBn;
    private String web;
    private String email;
    private String logoUrl;

}
