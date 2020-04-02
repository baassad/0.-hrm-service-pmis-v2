package com.cokreates.grp.beans.organogramDTO;


import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OfficeLayerDTO {

    private String oid;
    private String nameEn;
    private String nameBn;
    private BigDecimal sortOrder;

    private String createdBy;
    private String updatedBy;

    private String isDeleted;

    private Timestamp createdOn;
    private Timestamp updatedOn;

}
