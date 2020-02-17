package com.cokreates.grp.qualification.publication;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Publication extends BaseEntity {

    private String publicationType, publicationName, publicationDate, description;
}