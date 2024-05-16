package com.ecommercewebsite.ecommercewebsite.order.mapper;

import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.product.mapper.ProductMapper;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.product.repository.ProductRepository;
import com.ecommercewebsite.ecommercewebsite.useraddress.mapper.UserAddressMapper;
import com.ecommercewebsite.ecommercewebsite.useraddress.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ProductMapper productMapper;
    private final UserAddressMapper userAddressMapper;
    private final ProductRepository productRepository;
    private final UserAddressRepository userAddressRepository;
    public OrderEntity create(OrderRequest orderRequest) {
        return OrderEntity
                .builder()
                .deliveryStatus(orderRequest.getDeliveryStatus())
                .productEntities(new HashSet<>(productRepository.findAllById(orderRequest.getProducts().stream().map(ProductResponse::getId).toList())))
                .orderRefNo(UUID.randomUUID().toString())
                .userAddressEntity(userAddressRepository.findById(orderRequest.getUserAddress().getId()).orElseGet(null))
                .build();
    }
    public OrderResponse toResponse(OrderEntity orderEntity)
    {
        return OrderResponse
                .builder()
                .id(orderEntity.getId())
                .deliveryStatus(orderEntity.getDeliveryStatus())
                .orderRefNo(orderEntity.getOrderRefNo())
                .products(orderEntity.getProductEntities().stream().map(productMapper::toResponse).collect(Collectors.toSet()))
                .userAddress(userAddressMapper.toResponse(orderEntity.getUserAddressEntity()))
                .build();
    }
}
