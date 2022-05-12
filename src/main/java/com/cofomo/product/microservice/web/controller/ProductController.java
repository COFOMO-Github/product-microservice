package com.cofomo.product.microservice.web.controller;

import com.cofomo.product.microservice.dto.ProductDto;
import com.cofomo.product.microservice.mapper.MapStructMapper;
import com.cofomo.product.microservice.services.ProductService;
import com.cofomo.product.microservice.services.impl.ProductDetailServiceImpl;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import com.cofomo.product.microservice.wsdl.ProductDetail;
import io.swagger.api.ProductApi;
import io.swagger.model.Supplier;
import io.swagger.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin//(origins = "http://localhost:4200")
public class ProductController implements ProductApi {

    ProductService productService;
    MapStructMapper mapper;
    final ProductDetailServiceImpl productDetailService;

    @Override
    public ResponseEntity<Product> addProduct(Product product) {
        log.info("Ajout d'une nouvelle product : " + product.toString());
        ProductDto productDto = mapper.productToProductDto(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.productDtoToProduct(productService.addProduct(productDto))
        );
    }

    @Override
    public ResponseEntity<Boolean> deleteProduct(String id) {
        log.info("Suppression de la product dont l'ID est : " + id);
        productService.deleteProduct(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProduct(String id) {
        log.info("Envoi de la product dont l'ID est : " + id);
        ProductDto productDto = productService.getProductById(Long.parseLong(id));
        Product product = mapper.productDtoToProduct(productService.getProductById(Long.parseLong(id)));
        Supplier fournisseur = mapper.fournsseurDtoToFournisseur(
                productService.getFournisseurByReference(productDto.getReffrs()));
        product.setSupplier(fournisseur);

        ProductDetail productDetail = productDetailService.getProductDetail(productDto.getRefpdt());
        product.setProductDetail(mapper.mapToProductDetailResponse(productDetail));

        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
    }

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Envoi de la liste completes des products");
        List<Product> products = mapper.productListDtoToProductList(productService.getProductList());
        products = products.stream()
                .map(p -> {
                    ProductDto productDto = productService.getProductById(Long.parseLong(p.getId()));
                    try {
                        Supplier fournisseur = mapper.fournsseurDtoToFournisseur(
                                productService.getFournisseurByReference(productDto.getReffrs()));
                        p.setSupplier(fournisseur);

                        ProductDetail productDetail = productDetailService.getProductDetail(productDto.getRefpdt());
                        p.setProductDetail(mapper.mapToProductDetailResponse(productDetail));

                    } catch (NotFoundException e) {
                        log.error("Couldn't retrieve Supplier with reference = " + productDto.getReffrs(), e);
                    }
                    return p;
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }

    @GetMapping("/getProductDetails")
    public ResponseEntity<ProductDetail> getProductDetail(@RequestParam String reference){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productDetailService.getProductDetail(reference));
    }
}
