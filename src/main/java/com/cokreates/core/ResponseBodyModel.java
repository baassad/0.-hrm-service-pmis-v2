package com.cokreates.core;

import lombok.Data;

import java.util.List;

@Data
public class ResponseBodyModel<T> {
    private List<T> data;
}
