package com.ecommercewebsite.ecommercewebsite.criteriaqueries.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CriteriaRequest {
    private final String  itemName;
    private final String itemDescription;
    private final int itemPrice;
}
