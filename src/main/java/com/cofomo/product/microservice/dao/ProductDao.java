package com.cofomo.product.microservice.dao;

import com.cofomo.product.microservice.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity,Long> {
}
