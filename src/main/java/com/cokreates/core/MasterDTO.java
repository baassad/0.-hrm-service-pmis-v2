package com.cokreates.core;

import java.sql.Date;
import java.sql.Timestamp;

import com.cokreates.grp.util.request.RequestBodyDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterDTO implements RequestBodyDTO {

    private String oid;

    private String nodeOid;

    private String config;

    private Object main;

    private Object temp;

    private Object node;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String dataStatus;
}
