package com.cofomo.product.microservice.mapper;

import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.wsdl.ProductDetail;
import io.swagger.model.Fournisseur;
import io.swagger.model.Product;
import io.swagger.model.ProductDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {


    Product productDtoToProduct(ProductDto product);

    ProductDto productToProductDto(Product product);

    ProductEntity productDtoToProductEntity(ProductDto product);

    ProductDto productEntityToProductDto(ProductEntity product);

    Fournisseur fournsseurDtoToFournisseur(FournisseurDto fournisseurDto);

    List<Product> productListDtoToProductList(List<ProductDto> product);

    List<ProductDto> productListEntityToProductDtoList(List<ProductEntity> product);

    ProductDetail mapToProductDetail(ProductDetailResponse productDetailResponse);
    ProductDetailResponse mapToProductDetailResponse(ProductDetail productDetail);

}
