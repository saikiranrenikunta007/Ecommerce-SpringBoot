package com.ecommercewebsite.ecommercewebsite.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagingResponse <P>{
    int recordCount;
    P response;
}
