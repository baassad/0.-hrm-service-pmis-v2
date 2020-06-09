package com.cokreates.grp.beans.organogramDTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
