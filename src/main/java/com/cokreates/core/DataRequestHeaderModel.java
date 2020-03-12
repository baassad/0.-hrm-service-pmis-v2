package com.cokreates.core;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class DataRequestHeaderModel {

    @NotBlank
    private String requestId;

    @NotBlank
    private String requestSource;

    @NotBlank
    private String requestSourceService;

    @NotBlank
    private String requestClient;

    @NotBlank
    private String requestType;

    @NotNull
    private Date requestTime;

    @NotBlank
    private String requestVersion;

    @NotNull
    private Integer requestTimeoutInSeconds;

    @NotNull
    @Min(0)
    private Integer requestRetryCount;

    @Min(0)
    private Integer hopCount;

    private String traceId;

    private String requestUrl;
}
