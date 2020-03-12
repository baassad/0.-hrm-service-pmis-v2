package com.cokreates.grp.beans.history;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class History extends BaseEntity {

    private String modificationType, nodeName, comment;
    private Map<String, String> oldValue;
    private Map<String, String> newValue;
}
