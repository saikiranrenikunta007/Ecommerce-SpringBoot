package com.ecommercewebsite.ecommercewebsite.criteriaqueries.service;

import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaRequest;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaResponse;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
public interface CriteriaService {
    public List<CriteriaResponse> getAllItems();
    public CriteriaResponse addItem(CriteriaRequest criteriaRequest);

    List<CriteriaResponse> getItems();
    List<CriteriaResponse> sortItems();
    List<Double> getAvg();
    void updateItem();
}
