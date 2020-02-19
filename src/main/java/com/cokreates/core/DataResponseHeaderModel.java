package com.cokreates.core;

import lombok.Data;

import java.util.Date;

@Data
public class DataResponseHeaderModel {

    private Date requestReceivedTime, responseTime;

    private int hopCount;

    private long responseProcessingTimeInMs;
    private String requestId, responseCode, responseMessage, responseVersion, requestSourceService, traceId;
}
