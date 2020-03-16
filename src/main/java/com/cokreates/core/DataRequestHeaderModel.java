package com.cokreates.core;

import lombok.Data;

import java.util.Date;


@Data
public class DataRequestHeaderModel {

    private String requestId;

    private String requestSource;

    private String requestSourceService;

    private String requestClient;

    private String requestType;

    private Date requestTime;

    private String requestVersion;


    private Integer requestTimeoutInSeconds;


    private Integer requestRetryCount;

    private Integer hopCount;

    private String traceId;

    private String requestUrl;
}
