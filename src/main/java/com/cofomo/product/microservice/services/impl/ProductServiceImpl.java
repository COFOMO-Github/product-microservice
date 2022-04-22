package com.cofomo.product.microservice.services.impl;


import com.cofomo.product.microservice.dao.ProductDao;
import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.mapper.MapStructMapper;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.services.ProductService;
import com.cofomo.product.microservice.services.WebClientService;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    public final MapStructMapper mapper;
    private final WebClientService webClientService;

    @Override
    public List<ProductDto> getProductList() {
        log.info("Service : Envoi de la liste completes des products");
        return mapper.productListEntityToProductDtoList(productDao.findAll());
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Service : Envoi de la product dont l'ID est : " + id);
        ProductEntity productEntity = productDao.findById(id).orElseThrow(()
                -> new NotFoundException(ProductEntity.class, id));
        return mapper.productEntityToProductDto(productEntity);
    }

    @Override
    public ProductDto addProduct(ProductDto product) {
        log.info("Service : Ajout d'une nouvelle product  : " + product.toString());
        ProductEntity productEntity = mapper.productDtoToProductEntity(product);
        return mapper.productEntityToProductDto(productDao.save(productEntity));
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Service : Suppression de la product dont l'ID est : " + id);
        getProductById(id);
        productDao.deleteById(id);
    }

    @Override
    public FournisseurDto getFournisseurByReference(String id) {
        FournisseurDto fournisseurDto = webClientService.getFournisseurByReference(id);
        return fournisseurDto;
    }


}
