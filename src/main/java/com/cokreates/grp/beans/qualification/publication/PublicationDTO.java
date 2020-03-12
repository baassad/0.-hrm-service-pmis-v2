package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublicationDTO extends MasterDTO {

    private String publicationType, publicationName, description;
    private Date publicationDate;
}
