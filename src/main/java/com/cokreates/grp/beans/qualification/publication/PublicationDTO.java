package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublicationDTO extends MasterDTO {

    private String publicationType, publicationName, description;
    private String publicationDate;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
