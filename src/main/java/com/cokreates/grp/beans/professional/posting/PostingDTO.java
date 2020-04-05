package com.cokreates.grp.beans.professional.posting;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostingDTO extends MasterDTO {
    private String post;
    private String organization;
    private String location;
    private String payScale;
    private String payLastDrawn;
    private Date from, to;
    private String district, city, grade;

}
