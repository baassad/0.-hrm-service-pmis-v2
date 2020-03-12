package com.cokreates.core;

import lombok.Data;

import java.util.Map;

@Data
public class RequestModel<T> {

     private DataRequestHeaderModel header;
     private Map<String,Object> meta;
     private RequestBodyModel<T> body;

}
