package com.cokreates.core;

import lombok.Data;

import java.util.List;

@Data
public class RequestBodyModel<T> {

    private String employeeOid;
    private List<T> data;
}
