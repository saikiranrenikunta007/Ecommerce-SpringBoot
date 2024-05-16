package com.ecommercewebsite.ecommercewebsite.product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class ProductResponse {
    private final String id;
    private final String name;
    private final String description;
}
