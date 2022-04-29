package com.cofomo.product.microservice.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.cofomo.product.microservice.utils.Constants.MESSG_ERR;


@Getter
public enum FunctionalErrorCode {

    BAD_REQUEST( HttpStatus.BAD_REQUEST, "Bad Request"),
    NOT_FOUND( HttpStatus.NOT_FOUND, "Not Found"),
    NOT_FOUND_MESSAGE( HttpStatus.NOT_FOUND, "%s"),
    NOT_FOUND_ENTITY_ID( HttpStatus.NOT_FOUND, MESSG_ERR),
    NOT_FOUND_ENTITY_VALUE( HttpStatus.NOT_FOUND, "No record of type %s and with value : %s is present in the database"),
    ;

    private final HttpStatus httpStatus;
    private final String messageTemplate;

    FunctionalErrorCode(HttpStatus httpStatus, String messageTemplate) {
        this.httpStatus = httpStatus;
        this.messageTemplate = messageTemplate;
    }

}
