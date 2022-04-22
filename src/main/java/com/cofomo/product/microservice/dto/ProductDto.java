package com.cofomo.product.microservice.dto;

import lombok.*;

import javax.persistence.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @Id
    private Long id;

    private String name;

    private Double price;

    private String reference;


}
