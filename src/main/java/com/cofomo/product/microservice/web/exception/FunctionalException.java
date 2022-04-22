package com.cofomo.product.microservice.web.exception;

import lombok.Getter;

import static java.lang.String.format;


public class FunctionalException extends RuntimeException {

    private static final long serialVersionUID = 4273322132185545866L;

    @Getter
    private final FunctionalErrorCode errorCode;

    FunctionalException(String messageTemplate, FunctionalErrorCode errorCode, String... arguments) {
        super(format(messageTemplate, (Object[]) arguments));
        this.errorCode = errorCode;
    }

    FunctionalException(FunctionalErrorCode errorCode, String... arguments) {
        this(errorCode.getMessageTemplate(), errorCode, arguments);
    }
}