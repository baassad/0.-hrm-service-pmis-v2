package com.cokreates.core;

import lombok.Data;

import java.util.List;

@Data
public class RequestBodyModel<T> {
    private List<T> data;
}
