package com.ecommercewebsite.ecommercewebsite.category.repository;

import com.ecommercewebsite.ecommercewebsite.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity,String> {
}
