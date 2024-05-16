package com.ecommercewebsite.ecommercewebsite.category.controller;

import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryResponse;
import com.ecommercewebsite.ecommercewebsite.category.service.CategoryService;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
/**
 * This is Category controller class
 * This class is responsible for all operations on categories.
 */
public class CategoryController {
    private CategoryService categoryService;

    /**
     * This method is responsible for retrieving all categories in the repository.
     *
     * @return CategoryResponse Payload.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> retrieveAllCategories() {
        List<CategoryResponse> categoryResponses = categoryService.retrieveAllCategories();
        return ResponseEntity.ok(categoryResponses);
    }

    /**
     * This method is responsible for retrieving specific category products
     *
     * @param categoryId
     * @return list of category response payloads.
     */
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductResponse>> retrieveSpecificCategoryProducts(@PathVariable String categoryId) {

        List<ProductResponse> productResponses = categoryService.retrieveSpecificCategoryProducts(categoryId);
        return ResponseEntity.ok(productResponses);
    }

    /**
     * This method is responsible for retrieving specific product  in the category.
     *
     * @param categoryId
     * @param productId
     * @return ProductResponse payload.
     */
    @GetMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductResponse> retrieveSpecificProduct(@PathVariable String categoryId, @PathVariable String productId) {

        ProductResponse productResponse = categoryService.retrieveSpecificProduct(categoryId, productId);
        return ResponseEntity.ok(productResponse);
    }

    /**
     * This method is responsible for deletion of all categories.
     *
     * @return ResponseObject with status code 204
     */
    @DeleteMapping("/categories")
    public ResponseEntity<Object> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return ResponseEntity.noContent().build();
    }

    /**
     * This method is responsible for deletion of specific category.
     *
     * @param categoryId
     * @return Response Object with status code 204.
     */
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Object> deleteSpecificCategory(@PathVariable String categoryId) {
        categoryService.deleteSpecificCategory(categoryId);
        return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
    }

    /**
     * This method is responsible for deletion of specific product that present in the category.
     *
     * @param categoryId
     * @param productId
     * @return ResponseObject with status code 204.
     */
    @DeleteMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<Object> deleteSpecificProduct(@PathVariable String categoryId, @PathVariable String productId) {
        categoryService.deleteSpecificProduct(categoryId, productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * This method is responsible to add a new category.
     *
     * @param categoryRequest
     * @return CategoryResponse Payload.
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    /**
     * This method is responsible for adding a product into specific category.
     *
     * @param categoryId
     * @param productRequest
     * @return ProductResponse payload.
     */
    @PostMapping("/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> addProduct(@PathVariable String categoryId, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = categoryService.addProduct(categoryId, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);

    }

    /**
     * This method is responsbile for updating a specific category.
     *
     * @param categoryId
     * @param categoryRequest
     * @return CategoryResponse payload.
     */
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable String categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategoryById(categoryId, categoryRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryResponse);
    }

}
