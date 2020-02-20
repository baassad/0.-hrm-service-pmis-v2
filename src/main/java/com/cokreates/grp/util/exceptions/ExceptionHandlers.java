package com.cokreates.grp.util.exceptions;

import com.cokreates.core.*;
import com.cokreates.grp.config.ServiceConfiguration;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.components.RequestContextComponent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers {

    @Autowired
    private RequestContextComponent requestContextComponent;


    private final ServiceConfiguration serviceConfiguration;
    private final HeaderUtilComponent headerUtilComponent;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceExceptionHolder.ResourceNotFoundException.class)
    public ResponseModel handleResourceNotFoundException(final ServiceExceptionHolder.ResourceNotFoundException ex) {
        return getProcessedApiErrorResponse(new ApiErrorResponse(ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException.class)
    public ResponseModel handleResourceNotFoundException(final ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException ex) {
        return getProcessedApiErrorResponse(new ApiErrorResponse(ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseModel handleValidationException(BindException ex) {
        return getProcessedApiErrorResponse(new ApiErrorResponse("Validation failed"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseModel methodArgumentNotValidExceptionEntity(MethodArgumentNotValidException ex) {
        Errors validationErrors = ex.getBindingResult();
        String message = "Validation failed. " + validationErrors.getErrorCount() + " error(s)";

        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : validationErrors.getFieldErrors())
            errors.add(Objects.requireNonNull(fieldError).getField() + " " + fieldError.getDefaultMessage());

        for (ObjectError objectError : validationErrors.getGlobalErrors())
            errors.add(objectError.getDefaultMessage());

        return getProcessedApiErrorResponse(new ApiErrorResponse(message, errors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, UnsupportedOperationException.class})
    @ResponseBody
    public ResponseModel handleIllegalArgumentException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return getProcessedApiErrorResponse(new ApiErrorResponse("Internal server error while processing request.",ex.getClass().getCanonicalName()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseModel handleRuntimeException(RuntimeException ex) {
        return getProcessedApiErrorResponse(new ApiErrorResponse("Request execution failed"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseModel handleNoHandlerFoundError(final NoHandlerFoundException e) {
        return getProcessedApiErrorResponse(new ApiErrorResponse("Invalid URL"));
    }

    private ResponseModel<String> getProcessedApiErrorResponse(ApiErrorResponse apiErrorResponse) {
        log.error(apiErrorResponse.toString());
        RequestModel requestDTO = requestContextComponent.getRequestDTO();

        DataResponseHeaderModel header = requestDTO != null ?
                headerUtilComponent.getResponseHeaderDTO(requestDTO.getHeader()) :
                headerUtilComponent.getResponseHeaderDTO(apiErrorResponse);

        header.setResponseMessage(apiErrorResponse.getMessage());

        ResponseBodyModel<String> body = new ResponseBodyModel<>();
        body.setData(apiErrorResponse.getErrors());

        return new ResponseModel<>(header, new HashMap<>(), body);
    }

    @Data
    @Setter(AccessLevel.PRIVATE)
    public class ApiErrorResponse {

        private DataRequestHeaderModel header;
        private final String code;
        private final String message;
        private final List<String> errors;

        private ApiErrorResponse(String message, List<String> errors, ServiceExceptionHolder.ServiceException e) {
            this.code = serviceConfiguration.getShortCode() + e.getCode();
            this.message = message;
            this.errors = errors;
        }

        private ApiErrorResponse(String message, ServiceExceptionHolder.ServiceException e) {
            this(message, new ArrayList<>(), e);
        }

        private ApiErrorResponse(String message, List<String> errors) {
            this(message, errors, new ServiceExceptionHolder.ServiceException(1000, message));
        }

        private ApiErrorResponse(String message) {
            this(message, Collections.singletonList(message));
        }

        private ApiErrorResponse(String message, String errorMessage) {
            this(message, Collections.singletonList(errorMessage));
        }
    }
}
