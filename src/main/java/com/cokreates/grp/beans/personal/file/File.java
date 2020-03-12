package com.cokreates.grp.beans.personal.file;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class File extends BaseEntity {

    private String attachmentId;
    private String type;
    private Date issueDate;
    private Date expiryDate;
    //tempData obj
}