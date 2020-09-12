package com.cokreates.grp.beans.common;

import lombok.Data;

@Data
public class Comment {
    private ApproverCommentDTO approver;
    private Object reviewer;
    private Object requester;
}
