package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Publication extends BaseEntity {

    private String publicationType, publicationName, description;
    private Date publicationDate;
}