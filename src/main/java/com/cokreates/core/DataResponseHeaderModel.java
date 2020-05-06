package com.cokreates.core;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponseHeaderModel {

    private Timestamp requestReceivedTime, responseTime;

    private int hopCount;

    private long responseProcessingTimeInMs;
    private String requestId, responseCode, responseMessage, responseVersion, requestSourceService, traceId;
}
