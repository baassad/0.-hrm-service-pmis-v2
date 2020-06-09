package com.cokreates.grp.beans.organogramDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private String goCode;
    
    private OfficeLayerDTO officeLayer;

    private String codeEn;
    private String codeBn;
    private String addressEn;
    private String addressBn;
    private String web;
    private String email;
    private String logoUrl;

}
