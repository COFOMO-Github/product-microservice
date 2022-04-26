package com.cofomo.product.microservice.services;


import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductList();

    ProductDto getProductById(Long id);

    ProductDto addProduct(ProductDto product);

    void deleteProduct(Long id);

    FournisseurDto getFournisseurByReference(String reffrs);

    FournisseurDto getFournisseurById(String id);

}
