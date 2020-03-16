package com.cokreates.core;

import com.cokreates.grp.beans.common.Comment;
import com.cokreates.grp.util.request.RequestBodyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterRequestDTO extends MasterDTO implements RequestBodyDTO {

    private String approvalHistoryOid;

    private Comment comment;

    private String approvalStatus;
}
