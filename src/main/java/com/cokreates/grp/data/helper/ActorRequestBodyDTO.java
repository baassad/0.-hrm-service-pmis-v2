package com.cokreates.grp.data.helper;

import lombok.Data;

@Data
public class ActorRequestBodyDTO {

    private String requesterOid;

    private String reviewerOid;

    private String approverOid;

    private MiscellaneousRequestProperty miscellaneousRequestProperty;

}
