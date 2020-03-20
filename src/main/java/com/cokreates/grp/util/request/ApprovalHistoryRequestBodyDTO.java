package com.cokreates.grp.util.request;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.Change;
import com.cokreates.grp.beans.common.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistoryRequestBodyDTO extends MasterDTO {
    private String status;
    private Object comment;
}
