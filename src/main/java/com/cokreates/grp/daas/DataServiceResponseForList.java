package com.cokreates.grp.daas;

import lombok.Data;

@Data
public class DataServiceResponseForList<T> {

    DataServiceResponseHeader header;

    DataServiceResponseBodyForList<T> body;
}
