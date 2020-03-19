package com.cokreates.grp.beans.common;

import lombok.Data;

@Data
public class Comment {
    private String reviewerOid;
    private String messsage;
    private String dateAndTime;

    private String approver;
    private String approverOid;

    private String requester;
    private String requesterOid;
}
