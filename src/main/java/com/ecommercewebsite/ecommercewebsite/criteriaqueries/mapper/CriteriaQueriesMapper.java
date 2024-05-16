package com.ecommercewebsite.ecommercewebsite.criteriaqueries.mapper;

import com.ecommercewebsite.ecommercewebsite.criteriaqueries.Entity.CriteriaQueries;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaRequest;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class CriteriaQueriesMapper {
    public CriteriaQueries create(CriteriaRequest criteriaRequest)
    {
        return CriteriaQueries
                .builder()
                .itemId(UUID.randomUUID().toString())
                .itemDescription(criteriaRequest.getItemDescription())
                .itemPrice(criteriaRequest.getItemPrice())
                .itemName(criteriaRequest.getItemName())
                .build();
    }
    public CriteriaResponse toResponse(CriteriaQueries criteriaQueries)
    {
        return CriteriaResponse
                .builder()
                .itemName(criteriaQueries.getItemName())
                .itemDescription(criteriaQueries.getItemDescription())
                .itemPrice(criteriaQueries.getItemPrice())
                .build();
    }
}
