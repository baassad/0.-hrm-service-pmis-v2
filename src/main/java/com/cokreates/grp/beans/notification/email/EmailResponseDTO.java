package com.cokreates.grp.beans.notification.email;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailResponseDTO extends MasterDTO {
    private int status;
    private int totalCount;
    private String message;
    private String errors;
    private String options;
    private Timestamp timestamp;

    DataEmailResponseDTO data;
}
