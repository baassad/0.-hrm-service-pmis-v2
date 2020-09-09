package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicationListResponseBodyDTO extends MasterDTO {

    private String title;
    private String description;
    private Date dateOfReceiving;
    private String givenBy;
    private String countryOid;
    private String isLocal;
    private String isForeign;
    private String tags;
    private String approvalStatus;
    private String goRequired;

    private String typeOid;
    private String recipientOid;

    private String typeNameEn;
    private String typeNameBn;
    private String countryNameEn;
    private String countryNameBn;

    private String recipientOfficeOid;
    private String recipientOfficeUnitOid;
    private String recipientOfficeUnitPostOid;
    private String recipientNameEn;
    private String recipientNameBn;
    private String recipientOfficeNameEn;
    private String recipientOfficeNameBn;
    private String recipientOfficeUnitNameEn;
    private String recipientOfficeUnitNameBn;
    private String recipientOfficeUnitPostNameEn;
    private String recipientOfficeUnitPostNameBn;

}
