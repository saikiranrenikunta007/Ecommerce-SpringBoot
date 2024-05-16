package com.ecommercewebsite.ecommercewebsite.order.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class OrderPaymentRequest {
    private final List<String> productIds;
    private final String userAddressId;
}
