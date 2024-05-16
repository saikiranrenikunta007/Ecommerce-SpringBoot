package com.ecommercewebsite.ecommercewebsite.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class OrderPaymentResponse {
    private final String orderId;
    private final double amount;
    private final String orderRefNo;
}
