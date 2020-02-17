package com.cokreates.grp.personal.file;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class File extends BaseEntity {

    private String attachmentId;
    private String type;
    private String issueDate;
    private String expiryDate;
    //tempData obj
}