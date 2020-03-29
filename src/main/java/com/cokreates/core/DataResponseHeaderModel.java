package com.cokreates.core;

import java.sql.Date;

import lombok.Data;

@Data
public class DataResponseHeaderModel {

    private Date requestReceivedTime, responseTime;

    private int hopCount;

    private long responseProcessingTimeInMs;
    private String requestId, responseCode, responseMessage, responseVersion, requestSourceService, traceId;
}
