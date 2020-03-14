package com.cokreates.grp.beans.professional.posting;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostingDTO extends MasterDTO {
    private String post;
    private String organization;
    private String location;
    private String paySacle;
    private String payLastDrawn;
    private Date from;
    private Date to;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
