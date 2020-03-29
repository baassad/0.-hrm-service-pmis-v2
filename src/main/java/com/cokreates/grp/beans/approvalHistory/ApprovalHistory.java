package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.BaseEntity;
import com.cokreates.grp.beans.common.Change;
import com.cokreates.grp.beans.common.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistory extends BaseEntity {

    private String changeType, status, nodeName, employeeOid, isDeleted;
    private Comment comment;
    private Change change;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;
}
