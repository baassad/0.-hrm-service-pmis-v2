package com.cokreates.grp.beans.history;

import java.util.Map;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryDTO extends MasterDTO {

    private String modificationType, nodeName, comment;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;

    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String config;
    private String dataStatus;
}
