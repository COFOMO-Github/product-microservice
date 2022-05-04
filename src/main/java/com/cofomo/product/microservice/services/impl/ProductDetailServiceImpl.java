package com.cofomo.product.microservice.services.impl;

import com.cofomo.product.microservice.services.ProductDetailService;
import com.cofomo.product.microservice.wsdl.ProductDetail;
import com.cofomo.product.microservice.wsdl.ProductDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final Jaxb2Marshaller marshaller;

    @Override
    public ProductDetail getProductDetail(String reference) {
        ProductDetailRequest request = new ProductDetailRequest();
        request.setReference(reference);

        WebServiceTemplate template = new WebServiceTemplate(marshaller);
        return (ProductDetail) template.marshalSendAndReceive("http://localhost:8088/api/v1/ws", request);
    }


}
