package com.ecommercewebsite.ecommercewebsite.order.mapper;

import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMapper {

    public OrderPaymentResponse toResponse(OrderEntity orderEntity, double totalAmount) {
        return OrderPaymentResponse.builder()
                .orderId(orderEntity.getId())
                .orderRefNo(orderEntity.getOrderRefNo())
                .amount(totalAmount)
                .build();
    }
}
