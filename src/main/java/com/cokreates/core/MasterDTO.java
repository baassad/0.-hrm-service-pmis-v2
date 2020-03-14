package com.cokreates.core;

import com.cokreates.grp.util.request.RequestBodyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MasterDTO<T extends  MasterDTO> implements RequestBodyDTO {

    private String oid;

    private String nodeOid;

    private String config;

    private T main;

    private T temp;

    private T node;

    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String dataStatus;
}
