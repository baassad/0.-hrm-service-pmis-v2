package com.cokreates.grp.util.request;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApprovalHistoryRequestBodyDTO extends MasterDTO {
    private String status;
    private Object comment;
}
