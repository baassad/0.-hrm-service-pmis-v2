package com.cokreates.grp.beans.approvalHistory;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {

    String approverOid;

    String reviewerOid;

    String requesterOid;

    String message;

    String nameBn;

    String postNameBn;

    Date dateAndTime;

}
