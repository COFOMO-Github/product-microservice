package com.cofomo.product.microservice.test.unitTest.service;

import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.cofomo.product.microservice.web.exception.FunctionalErrorCode.NOT_FOUND_ENTITY_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ServiceTest extends AbstractServiceTest {


    @Test
    public void getAllProductsTest() {
        //WHEN
        List<ProductDto> products = productService.getProductList();
        //THEN
        assertThat(products).isNotNull().isNotEmpty();
    }

    @Test
    public void findProductByIdTest() {
        //WHEN
        ProductDto output = productService.getProductById(product2.getId());

        //THEN
        assertNotNull(output);
        assertEquals(product2.getName(), output.getName());
        assertEquals(product2.getId(), output.getId());
        assertEquals(product2.getPrice(), output.getPrice());
    }

    @Test
    public void findProductByIdShouldThrowNotFoundExceptionTest() {
        //WHEN
        Throwable throwable = catchThrowable(() -> productService.getProductById(NOT_EXISTING_ID));

        //THEN
        assertThat(throwable).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void saveProductTest() {
        //GIVEN
        ProductDto product = ProductDto.builder()
                .name("Samsung S20 Ultra")
                .price(7700.90)
                .build();

        //WHEN
        ProductDto output = productService.addProduct(product);

        //THEN
        assertNotNull(output);
        assertNotNull(output.getId());
        assertEquals(product.getName(), output.getName());
        assertEquals(product.getPrice(), output.getPrice());
    }

    @Test
    public void deleteProductTest() {
        //GIVEN
        assertNotNull(product2);
        assertNotNull(product2.getId());

        //WHEN
        productService.deleteProduct(product2.getId());
        Throwable throwable = catchThrowable(() -> productService.getProductById(product2.getId()));

        //THEN
        assertThat(throwable).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void deleteProductShouldThrowNotFoundExceptionTest() {

        Throwable throwable = catchThrowable(() -> productService.deleteProduct(NOT_EXISTING_ID));
        assertThat(throwable).isInstanceOf(NotFoundException.class);
        assertEquals(throwable.getMessage(),
                String.format(NOT_FOUND_ENTITY_ID.getMessageTemplate(), ProductEntity.class.getSimpleName(), NOT_EXISTING_ID));
    }

    @Test
    public void testProductEntityToString() {
        ToStringVerifier.forClass(ProductEntity.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }


    @Test
    public void testHashCode() {

        ProductEntity productEntity1 = product1;
        ProductEntity productEntity2 = product1;

        assertEquals(productEntity1, productEntity2);
        assertEquals(productEntity2, productEntity1);
        assertEquals(productEntity1.hashCode(), productEntity2.hashCode());
    }


//    @Test
//    public void canEqualTest() {
//
//        ProductEntity productEntity1 = product1;
//
//        ProductEntity.canEqual(productEntity1);
//
//    }


}
