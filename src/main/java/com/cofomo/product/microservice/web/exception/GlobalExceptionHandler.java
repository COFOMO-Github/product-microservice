package com.cofomo.product.microservice.web.exception;

import com.cofomo.product.microservice.dao.error.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<RestError> handleFunctionalException(FunctionalException exception) {
        return ResponseEntity
                .status(exception.getErrorCode().getHttpStatus())
                .body(RestError.builder()
                        .httpStatusCode(exception.getErrorCode().getHttpStatus().value())
                        .httpStatus(exception.getErrorCode().getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }


}

