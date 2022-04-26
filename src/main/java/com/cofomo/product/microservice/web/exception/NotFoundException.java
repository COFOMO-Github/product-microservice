package com.cofomo.product.microservice.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.cofomo.product.microservice.web.exception.FunctionalErrorCode.NOT_FOUND_ENTITY_ID;
import static com.cofomo.product.microservice.web.exception.FunctionalErrorCode.NOT_FOUND_MESSAGE;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    /*public NotFoundException() {
        super(NOT_FOUND);
    }*/

    public NotFoundException(String message) {
        super(NOT_FOUND_MESSAGE,message);
    }

    public NotFoundException(Class<?> entityClass, Long id) {
        super(NOT_FOUND_ENTITY_ID, entityClass.getSimpleName(), String.valueOf(id));
    }

    /*public NotFoundException(Class<?> entityClass, String value) {
        super(NOT_FOUND_ENTITY_VALUE, entityClass.getSimpleName(), value);
    }*/

}
