package com.cokreates.grp.daas;

import lombok.Data;

import java.util.List;

@Data
public class DataServiceResponseBody<T> {

    private String oid;

    private String nodeOid;

    private List<Integer> rowcount;

    private T main;

    private T temp;

}
