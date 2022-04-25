package com.cofomo.product.microservice.test.unitTest.controller;


import com.cofomo.product.microservice.dao.error.RestError;
import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import io.swagger.model.Fournisseur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.cofomo.product.microservice.web.exception.FunctionalErrorCode.NOT_FOUND_ENTITY_ID;
import static com.cofomo.productmicroservice.utils.Constants.PRODUCT_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ControllerTest extends AbstractControllerTest {

    @BeforeEach
    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getProductList() throws Exception {
        String uri = PRODUCT_URL + "/list";

        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Iphone 13")
                .price(12000D)
                .reference("1")
                .build();
        FournisseurDto fournisseurDto = FournisseurDto.builder()
                .id(2L)
                .name("Iphone 13")
                .reference("1")
                .build();
        when(productService.getProductList()).thenReturn(productDtoList());
        when(productService.getProductById(any())).thenReturn(productDto);
        when(productService.getFournisseurByReference(any())).thenReturn(fournisseurDto);

        this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getProductById() throws Exception {
        Long id = 1L;
        String uri = PRODUCT_URL + "/" + id;

        when(productService.getProductById(id)).thenReturn(productDto1());

        this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getProductByIdShouldThrowNotFound() throws Exception {
        Long id = 9999L;
        String uri = PRODUCT_URL + "/" + id;

        when(productService.getProductById(id)).thenThrow(new NotFoundException(ProductEntity.class, id));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound()).andReturn();

        assertEquals(NOT_FOUND_ENTITY_ID.getHttpStatus().value(), mvcResult.getResponse().getStatus());

        RestError error = super.mapFromJson(mvcResult.getResponse().getContentAsString(), RestError.class);
        assertEquals(String.format(NOT_FOUND_ENTITY_ID.getMessageTemplate(), ProductEntity.class.getSimpleName(), id), error.getMessage());
    }


    @Test
    public void deleteProductTest() throws Exception {
        Long id = 1L;
        String uri = PRODUCT_URL + "/delete/" + id;

        when(productService.getProductById(id)).thenReturn(productDto1());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void addProductTest() throws Exception {
        String uri = PRODUCT_URL;

        String expectedString = super.mapToJson(product2());


        when(productService.addProduct(productDto1())).thenReturn(productDto1());

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(expectedString))
                .andExpect(status().isCreated()).andDo(print());

    }


}
