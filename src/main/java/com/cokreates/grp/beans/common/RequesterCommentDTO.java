package com.cokreates.grp.beans.common;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
public class RequesterCommentDTO {
    private String requesterOid;

    private String nameEn;
    private String nameBn;

    private String postnameBn;
    private String postnameEn;

    private String messsage;
    private Date dateAndTime;
}
