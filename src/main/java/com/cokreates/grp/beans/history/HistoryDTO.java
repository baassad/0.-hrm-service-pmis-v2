package com.cokreates.grp.beans.history;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.util.Map;

@Data
public class HistoryDTO extends MasterDTO {

    private String modificationType, nodeName, comment;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;


}
