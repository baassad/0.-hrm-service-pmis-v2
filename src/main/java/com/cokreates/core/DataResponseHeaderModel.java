package com.cokreates.core;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DataResponseHeaderModel {

    private Timestamp requestReceivedTime, responseTime;

    private int hopCount;

    private long responseProcessingTimeInMs;
    private String requestId, responseCode, responseMessage, responseVersion, requestSourceService, traceId;
}
