package com.cokreates.grp.beans.common;

import lombok.Data;

import java.util.List;

@Data
public class Change {
    private List<String> nodePath;
    private String nodeName;
    private Object newValue;
    private Object oldValue;
}
