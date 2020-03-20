package com.cokreates.grp.util.request;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActorRequestBodyDTO extends MasterDTO {
    private String requesterOid;
    private String reviewerOid;
    private String approverOid;
}
