package com.cokreates.grp.util.request;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.approvalHistory.CommentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistoryRequestBodyDTO extends MasterDTO {
    private String status;
    private CommentDTO comment;
}
