package com.ecommercewebsite.ecommercewebsite.userEntity;

import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
public class NodeBeanOrder {
    private String id;
    private String orderRefNo;
    private String deliveryStatus;
    private Set<ProductResponse>productEntities;
    private UserAddressResponse userAddressEntity;

}

