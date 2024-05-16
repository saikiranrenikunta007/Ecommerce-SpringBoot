package com.ecommercewebsite.ecommercewebsite.order.model;

import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private final String id;
    private final String orderRefNo;
    private final String deliveryStatus;
    private final Set<ProductResponse> products;
    private final UserAddressResponse userAddress;
}
