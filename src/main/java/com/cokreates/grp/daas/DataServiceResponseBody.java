package com.cokreates.grp.daas;

import lombok.Data;

@Data
public class DataServiceResponseBody<T> {

    private T main;

    private T temp;

}
