package com.ecommercewebsite.ecommercewebsite.product.mapper;

import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity create(ProductRequest productRequest) {
          return  ProductEntity.builder()
                  .manufacturerInfo(productRequest.getManufacturerInfo())
                  .name(productRequest.getName())
                  .price(productRequest.getPrice())
                  .description(productRequest.getDescription())
                  .quantity(productRequest.getQuantity())
                  .rating(productRequest.getRating())
                  .imageUrl1(productRequest.getImageUrl1())
                  .imageUrl2(productRequest.getImageUrl2())
                  .isDeliveryAvailable(productRequest.isDeliveryAvailable())
                  .build();

    }
    public ProductResponse toResponse(ProductEntity productEntity)
    {
        return ProductResponse.builder().id(productEntity.getId()).name(productEntity.getName()).description(productEntity.getDescription()).build();
    }


}

