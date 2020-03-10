package com.cokreates.grp.daas;

import lombok.Data;

@Data
public class DataServiceResponse<T> {

    DataServiceResponseBody<T> responseBody;

    DataServiceResponseHeader header;
}
