package com.cokreates.grp.util.exceptions;

import com.cokreates.core.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseModel handleValidationException(BindException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<ResponseData>(new ResponseData().error(HttpStatus.BAD_REQUEST.value(), null,
                errors, "Validation failed"),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseModel methodArgumentNotValidExceptionEntity(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<ResponseData>(
                new ResponseData().error(HttpStatus.BAD_REQUEST.value(), null,
                        errors, "Validation failed"),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, UnsupportedOperationException.class})
    @ResponseBody
    public ResponseModel handleIllegalArgumentException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<ResponseData>(
                new ResponseData().error(HttpStatus.BAD_REQUEST.value(), new ArrayList<String>() {{
                            add(ex.getMessage());
                        }},
                        null, "Operation failed"),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseModel handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<ResponseData>(new ResponseData()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        new ArrayList<String>() {{
                            add(ex.getMessage());
                        }}, null,
                        "Request execution failed"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
