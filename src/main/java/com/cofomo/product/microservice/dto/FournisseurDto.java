package com.cofomo.product.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto {

    @Id
    private Long id;

    private String name;

    private String matricule;

    private Long productId;

    private String reference;

}
