package com.ecommercewebsite.ecommercewebsite.criteriaqueries.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class CriteriaResponse{
    private final String  itemName;
    private final String itemDescription;
    private final int itemPrice;
}
