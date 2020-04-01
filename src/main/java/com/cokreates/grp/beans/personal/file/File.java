package com.cokreates.grp.beans.personal.file;

import java.sql.Date;

import com.cokreates.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class File extends BaseEntity {

    private String attachmentId;
    private String type;
    private Date issueDate, expiryDate;
    private String fileOid, fileName, fileExtension;
}