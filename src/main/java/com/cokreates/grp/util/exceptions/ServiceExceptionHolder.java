package com.cokreates.grp.util.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ServiceExceptionHolder {

    @Getter
    @RequiredArgsConstructor
    public static class ServiceException extends RuntimeException {
        private final int code;
        private final String message;
    }

    public static class ResourceNotFoundException extends ServiceException {
        public ResourceNotFoundException(String message) {
            super(400, message);
        }
    }

    public static class ResourceNotFoundDuringWriteRequestException extends ServiceException {
        public ResourceNotFoundDuringWriteRequestException(String message) {
            super(400, message);
        }
    }

    public static class ValidationException extends ServiceException{
        public ValidationException(String message){super(400,message);}
    }

    public static class TypeMismatchException extends ServiceException {
        public TypeMismatchException(String message) {
            super(400, message);
        }
    }

}
