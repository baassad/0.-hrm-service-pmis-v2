package com.cokreates.grp.daas;

import lombok.Data;

import java.util.List;

@Data
public class DataServiceResponseForList<T> {

    DataServiceResponseBody<T> body;
}
