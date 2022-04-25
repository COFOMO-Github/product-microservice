package com.cofomo.product.microservice.test.unitTest.service;


import com.cofomo.product.microservice.dao.ProductDao;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractServiceTest {

    @Autowired
    protected ProductDao productDao;
    @Autowired
    protected ProductService productService;

    protected static final Long NOT_EXISTING_ID = 99999L;

    protected ProductEntity product1 = new ProductEntity();
    protected ProductEntity product2 = new ProductEntity();
    protected ProductEntity product3 = new ProductEntity();

    @BeforeEach
    public void init() {
        product1 = productDao.save(product1());
        product2 = productDao.save(product2());
        product3 = productDao.save(product3());
    }

    @AfterEach
    public void clean() {
        productDao.deleteAll();
    }


    public ProductEntity product1() {
        return ProductEntity.builder()
                .id(null)
                .name("Iphone 11 PRO MAX")
                .price(7000D)
                .build();
    }

    public ProductEntity product2() {
        ProductEntity entity = new ProductEntity();
        entity.setId(null);
        entity.setName("IphoneX");
        entity.setPrice(4000D);
        return entity;
    }

    public ProductEntity product3() {
        return ProductEntity.builder()
                .id(null)
                .name("Iphone 13")
                .price(12000D)
                .build();
    }


}
