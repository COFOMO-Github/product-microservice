package com.cofomo.product.microservice.mapper;

import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.dto.UserDto;
import com.cofomo.product.microservice.model.ProductEntity;
import com.cofomo.product.microservice.model.UserEntity;
import com.cofomo.product.microservice.wsdl.ProductDetail;
import io.swagger.model.Supplier;
import io.swagger.model.Product;
import io.swagger.model.ProductDetailResponse;
import io.swagger.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "productDetail", ignore = true)
    Product productDtoToProduct(ProductDto product);

    ProductDto productToProductDto(Product product);

    ProductEntity productDtoToProductEntity(ProductDto product);

    ProductDto productEntityToProductDto(ProductEntity product);

    Supplier fournsseurDtoToFournisseur(FournisseurDto fournisseurDto);

    List<Product> productListDtoToProductList(List<ProductDto> product);

    List<ProductDto> productListEntityToProductDtoList(List<ProductEntity> product);

    ProductDetail mapToProductDetail(ProductDetailResponse productDetailResponse);

    ProductDetailResponse mapToProductDetailResponse(ProductDetail productDetail);


    /*** User Mapper ***/


    List<UserDto> userListEntityToUserDtoList(List<UserEntity> all);

    UserDto userEntityToUserDto(UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserDto user);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto addUser);

    List<User> userListDtoToUserList(List<UserDto> userList);
}
