package com.cofomo.product.microservice.model;

import lombok.*;

import javax.persistence.*;

import static com.cofomo.productmicroservice.utils.Constants.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = PRODUCT)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = NAME)
    private String name;

    @Column(name = PRICE)
    private Double price;

    @Column(name = REFPDT, unique = true)
    private String refpdt;

    @Column(name = REFFRS, unique = true)
    private String reffrs;

}
