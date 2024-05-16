package com.ecommercewebsite.ecommercewebsite.criteriaqueries.controller;

import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaRequest;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaResponse;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CriteriaQueriesController {
    private final CriteriaService criteriaService;
    @PostMapping("/items")
    public CriteriaResponse addItem(@RequestBody CriteriaRequest criteriaRequest)
    {
        return criteriaService.addItem(criteriaRequest);
    }
    @GetMapping("/items")
    public List<CriteriaResponse> retrieveAllItems()
    {
        return criteriaService.getAllItems();
    }
    @GetMapping("/items/duplicate")
    public List<CriteriaResponse> retrieveOnCondition()
    {
        return criteriaService.getItems();
    }
    @GetMapping("/items/sort")
    public List<CriteriaResponse> retrieveOnSort()
    {
        return criteriaService.sortItems();
    }
    @GetMapping("/items/avg")
    public List<Double> retrieveAvg()
    {
        return criteriaService.getAvg();
    }
    @PutMapping("/items")
    public void update()
    {
        criteriaService.updateItem();
    }

}
