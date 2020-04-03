package com.cokreates.core;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceRequestDTO<T> {

    private DataRequestHeaderModel header;

    private Map<String,Object> meta;

    private T body;

}
