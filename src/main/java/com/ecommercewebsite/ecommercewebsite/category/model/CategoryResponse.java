package com.ecommercewebsite.ecommercewebsite.category.model;

import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.model.PagingResponse;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private final String id;
    private final String name;
    private final String description;
    private final List<ProductResponse> products;
}
