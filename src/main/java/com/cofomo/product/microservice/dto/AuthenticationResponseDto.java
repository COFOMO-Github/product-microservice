package com.cofomo.product.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String username;
    private String password;
    private final String jwt;
}
