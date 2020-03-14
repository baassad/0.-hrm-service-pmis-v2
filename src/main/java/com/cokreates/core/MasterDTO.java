package com.cokreates.core;

import com.cokreates.grp.beans.common.Comment;
import com.cokreates.grp.util.request.RequestBodyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MasterDTO implements RequestBodyDTO {

    private String oid;

    private String nodeOid;

    private String config;

    private Object main;

    private Object temp;

    private Object node;

    private String approvalHistoryOid;

    private String approvalStatus;

    private Comment comment;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String dataStatus;
}
