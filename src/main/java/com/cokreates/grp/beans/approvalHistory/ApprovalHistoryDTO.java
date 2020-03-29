package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.Change;
import com.cokreates.grp.beans.common.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistoryDTO extends MasterDTO {

    private String status, nodeName, isDeleted;
    private Comment comment;
    private Change change;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;

    private String employeeOid;

    private String changeType;

}