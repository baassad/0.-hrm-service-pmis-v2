package com.cokreates.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModel<T> {

    private DataResponseHeaderModel header;
    private Map<String,Object> meta;
    private ResponseBodyModel<T> body;
}
