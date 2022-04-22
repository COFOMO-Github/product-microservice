package com.cofomo.product.microservice.mapper;

import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.model.ProductEntity;
import io.swagger.model.Fournisseur;
import io.swagger.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {


    Product productDtoToProduct(ProductDto product);

    ProductDto productToProductDto(Product product);

    ProductEntity productDtoToProductEntity(ProductDto product);

    ProductDto productEntityToProductDto(ProductEntity product);

    Fournisseur fournisseurEntityToFournisseurDto(FournisseurDto fournisseur);


    List<Product> productListDtoToProductList(List<ProductDto> product);

//    List<ProductDto> productListToProductDtoList(List<Product> product);


//    List<ProductEntity> productListDtoToProductEntityList(List<ProductDto> product);

    List<ProductDto> productListEntityToProductDtoList(List<ProductEntity> product);

}
