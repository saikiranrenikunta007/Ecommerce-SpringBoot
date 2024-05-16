package com.ecommercewebsite.ecommercewebsite.category.mapper;

import com.ecommercewebsite.ecommercewebsite.category.entity.CategoryEntity;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryResponse;
import com.ecommercewebsite.ecommercewebsite.product.mapper.ProductMapper;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class CategoryMapper {
     private final ProductMapper productMapper;
     public CategoryEntity create(CategoryRequest categoryRequest)
     {
         return CategoryEntity.builder()
                 .name(categoryRequest.getName())
                 .description(categoryRequest.getDescription())
                 .build();
     }
     public CategoryResponse toResponse(CategoryEntity categoryEntity)
     {
          List<ProductResponse> productResponses = new ArrayList<>();
          if(categoryEntity.getProductEntities()!=null)
          {
               productResponses.addAll(new ArrayList<>(categoryEntity.getProductEntities().stream().map(productMapper::toResponse).toList()));
          }
          return CategoryResponse.builder().id(categoryEntity.getId()).name(categoryEntity.getName())
                  .description(categoryEntity.getDescription())
                  .products(productResponses)
                  .build();

     }
}

