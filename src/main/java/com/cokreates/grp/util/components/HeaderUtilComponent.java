package com.cokreates.grp.util.components;

import com.cokreates.core.DataRequestHeaderModel;
import com.cokreates.core.DataResponseHeaderModel;
import com.cokreates.grp.config.ServiceConfiguration;
import com.cokreates.grp.util.exceptions.ExceptionHandlers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HeaderUtilComponent {

    private final ServiceConfiguration serviceConfiguration;

    public DataResponseHeaderModel getResponseHeaderDTO(DataRequestHeaderModel requestHeaderDTO) {
        DataResponseHeaderModel responseHeaderDTO = new DataResponseHeaderModel();

        responseHeaderDTO.setRequestReceivedTime(requestHeaderDTO.getRequestTime());
        responseHeaderDTO.setResponseTime(new Date(System.currentTimeMillis()));
        responseHeaderDTO.setHopCount(
                requestHeaderDTO.getHopCount() == null ? 1 : requestHeaderDTO.getHopCount() + 1
        );
        /*responseHeaderDTO.setResponseProcessingTimeInMs(Math.toIntExact(
                responseHeaderDTO.getResponseTime().getTime() - requestHeaderDTO.getRequestTime().getTime()
        ));*/
        responseHeaderDTO.setRequestId(requestHeaderDTO.getRequestId());
        responseHeaderDTO.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        responseHeaderDTO.setResponseMessage("Successfully performed");
        responseHeaderDTO.setResponseVersion("v1");
        responseHeaderDTO.setRequestSourceService(requestHeaderDTO.getRequestSourceService());
        responseHeaderDTO.setTraceId((requestHeaderDTO.getTraceId() == null || requestHeaderDTO.getTraceId().isEmpty()) ?
                UUID.randomUUID().toString() : requestHeaderDTO.getTraceId());
        return responseHeaderDTO;
    }

    public DataResponseHeaderModel getResponseHeaderDTO(ExceptionHandlers.ApiErrorResponse apiErrorResponse) {
        DataResponseHeaderModel responseHeaderDTO = new DataResponseHeaderModel();
        
        long millis = System.currentTimeMillis();  

        responseHeaderDTO.setRequestReceivedTime(new Date(millis));
        responseHeaderDTO.setResponseTime(new Date(millis));
        responseHeaderDTO.setHopCount(1);
        responseHeaderDTO.setResponseProcessingTimeInMs(Math.toIntExact(
                responseHeaderDTO.getResponseTime().getTime() - responseHeaderDTO.getRequestReceivedTime().getTime()
        ));
        responseHeaderDTO.setRequestId(UUID.randomUUID().toString());
        responseHeaderDTO.setResponseCode(apiErrorResponse.getCode());
        responseHeaderDTO.setResponseMessage(apiErrorResponse.getMessage());
        responseHeaderDTO.setResponseVersion(serviceConfiguration.getVersion());
        responseHeaderDTO.setTraceId(UUID.randomUUID().toString());
        return responseHeaderDTO;
    }

    public DataRequestHeaderModel getRequestHeaderDTO() {
        DataRequestHeaderModel requestHeaderDTO = new DataRequestHeaderModel();
        requestHeaderDTO.setRequestId( "random-uuid");
        requestHeaderDTO.setRequestSource("portal");
        requestHeaderDTO.setRequestSourceService("portal");
        requestHeaderDTO.setRequestClient("grp");
        requestHeaderDTO.setRequestType("random");
        requestHeaderDTO.setRequestTime(new Date(System.currentTimeMillis()));
        requestHeaderDTO.setRequestVersion("v1");
        requestHeaderDTO.setRequestTimeoutInSeconds(30);
        requestHeaderDTO.setRequestRetryCount(3);
        return requestHeaderDTO;

    }


}
