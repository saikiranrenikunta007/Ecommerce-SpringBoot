package com.ecommercewebsite.ecommercewebsite.product.repository;

import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    @Query(value = "select * from product b where b.name = :name", nativeQuery = true)
    Page<ProductEntity> findByName(String name,Pageable pageable);

}
