package com.cokreates.core;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseModel<T extends MasterDTO> {

    private DataResponseHeaderModel header;
    private Map<String,Object> meta;
    private ResponseBodyModel<T> body;
}
