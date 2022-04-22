package com.cofomo.product.microservice.services;


import com.cofomo.product.microservice.dto.FournisseurDto;

public interface WebClientService {
    FournisseurDto getFournisseurByReference(String id) ;
}
