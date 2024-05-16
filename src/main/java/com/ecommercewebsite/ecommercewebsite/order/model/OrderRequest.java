package com.ecommercewebsite.ecommercewebsite.order.model;

import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Set;
@RequiredArgsConstructor
@Getter
@Setter
public class OrderRequest {
    @NotNull
    @NotBlank
    private final String deliveryStatus;
    private final Set<ProductResponse> products;
    private final UserAddressResponse userAddress;
}

