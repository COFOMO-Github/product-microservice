package com.cofomo.product.microservice.test.unitTest.controller;


import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.mapper.MapStructMapper;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@WebAppConfiguration
public abstract class AbstractControllerTest {

    protected static String BASE_URL;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    protected ProductService productService;

    @Autowired
    protected MapStructMapper mapStructMapper;

    protected MockMvc mockMvc;


    @Value("${server.port}")
    protected void getBaseUrl(String port) {
        BASE_URL = "http://localhost:" + port + "/api/v1/product";
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, deserializer);
        objectMapper.registerModule(module);
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, deserializer);
        objectMapper.registerModule(module);
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectMapper.readValue(json, clazz);
    }


    public ProductEntity product1() {
        return ProductEntity.builder()
                .id(1L)
                .name("Iphone 11 PRO MAX")
                .price(7000D)
                .reffrs("reffrs")
                .refpdt("refpdt")
                .build();
    }
    public ProductDto productDto1() {
        return ProductDto.builder()
                .id(1L)
                .name("Iphone 11 PRO MAX")
                .price(7000D)
                .reffrs("reffrs")
                .refpdt("refpdt")
                .build();
    }

    public ProductEntity product2() {
        return ProductEntity.builder()
                .id(1L)
                .name("IphoneX")
                .price(4000D)
                .reffrs("reffrs")
                .refpdt("refpdt")
                .build();
    }

    public ProductEntity product3() {
        return ProductEntity.builder()
                .id(1L)
                .name("Iphone 13")
                .price(12000D)
                .reffrs("reffrs")
                .refpdt("refpdt")
                .build();
    }

    public List<ProductDto> productDtoList() {
        return new ImmutableList.Builder<ProductEntity>()
                .add(product1())
                .add(product2())
                .add(product3()).build().stream().map(productEntity ->
                        mapStructMapper.productEntityToProductDto(productEntity)).collect(Collectors.toList());
    }


}
