package com.cofomo.product.microservice.services.impl;

import com.cofomo.product.microservice.wsdl.ProductDetail;
import com.cofomo.product.microservice.wsdl.ProductDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class ProductDetailServiceImpl {

    @Autowired
    private Jaxb2Marshaller marshaller;

    public ProductDetail getProductDetail(String reference) {
        ProductDetailRequest request = new ProductDetailRequest();
        request.setReference(reference);

        WebServiceTemplate template = new WebServiceTemplate(marshaller);
        return (ProductDetail) template.marshalSendAndReceive("http://localhost:8088/api/v1/ws", request);
    }


}
