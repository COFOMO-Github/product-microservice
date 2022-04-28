package com.cofomo.product.microservice.services;

import com.cofomo.product.microservice.wsdl.ProductDetail;

public interface ProductDetailService {

    public ProductDetail getProductDetail(String reference);
}
