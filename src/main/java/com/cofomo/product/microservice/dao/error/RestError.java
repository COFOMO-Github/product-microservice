package com.cofomo.product.microservice.dao.error;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 *  this classe représente le bean qui va sérializer une erreur avec son code et son message.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestError {

    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String message;
}
