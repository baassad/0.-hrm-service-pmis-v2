package com.cokreates.grp.beans.common;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviewerCommentDTO extends MasterDTO {
    private String reviewerOid;

    private String nameEn;
    private String nameBn;

    private String postnameBn;
    private String postnameEn;

    private String messsage;
    private Date dateAndTime;
}
