package com.cokreates.core;

import lombok.Data;

import java.util.Map;

@Data
public class BlankRequestModel<T> {
     private DataRequestHeaderModel header;
     private Map<String,Object> meta;
     private BlankRequestBodyModel<T> body;
}
