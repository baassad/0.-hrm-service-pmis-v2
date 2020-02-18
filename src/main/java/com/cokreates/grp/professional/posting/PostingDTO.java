package com.cokreates.grp.professional.posting;

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

}
