package com.cofomo.product.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDto {

    private Long id;
    private String username;
    private final String jwt;
}
