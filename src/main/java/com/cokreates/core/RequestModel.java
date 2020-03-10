package com.cokreates.core;

import com.cokreates.grp.util.request.RequestBodyDTO;
import lombok.Data;

import java.util.Map;

@Data
public class RequestModel<T> {

     private DataRequestHeaderModel header;
     private Map<String,Object> meta;
     private RequestBodyModel<T> body;

}
