package com.cokreates.grp.util.request;

import com.cokreates.core.DataRequestHeaderModel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrganogramRequestDTO<T> {

    DataRequestHeaderModel header;
    Map<String, Object> meta = new HashMap<>();
    T body;
}
