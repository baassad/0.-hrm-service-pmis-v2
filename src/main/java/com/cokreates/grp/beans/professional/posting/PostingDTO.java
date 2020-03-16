package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostingDTO extends MasterDTO {
    private String post;
    private String organization;
    private String location;
    private String paySacle;
    private String payLastDrawn;
    private String from;
    private String to;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
