package com.cokreates.grp.beans.approvalHistory;

import java.util.Map;

import com.cokreates.core.MasterDTO;

import com.cokreates.grp.beans.common.Change;
import com.cokreates.grp.beans.common.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistoryDTO extends MasterDTO {

    private String status, nodeName;
    private Comment comment;
    private Change change;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;

//    private String employee_oid;
    private String employeeOid;

//    private String change_type;
    private String changeType;

    private String createdBy;
    private String updatedBy;
    private String createdOn;
    private String updatedOn;
    private String isDeleted;

//    private String created_by;
//    private String updated_by;
//    private String created_on;
//    private String updated_on;
//    private String is_deleted;
}