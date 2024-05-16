package com.ecommercewebsite.ecommercewebsite.category.service;

import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryResponse;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;

import java.util.List;

public interface CategoryService {
    /**
     * This method is responsible for retrieving all categories in the repository.
     *
     * @return CategoryResponse Payload.
     */
    public List<CategoryResponse> retrieveAllCategories();

    /**
     * This method is responsible for retrieving specific category products
     *
     * @param categoryId
     * @return list of category response payloads.
     */

    public List<ProductResponse> retrieveSpecificCategoryProducts(String categoryId);

    /**
     * This method is responsible for retrieving specific product  in the category.
     *
     * @param categoryId
     * @param productId
     * @return ProductResponse payload.
     */
    public ProductResponse retrieveSpecificProduct(String categoryId, String productId);


    /**
     * This method is responsible for deletion of all categories.
     *
     * @return ResponseObject with status code 204
     */

    public void deleteAllCategories();

    /**
     * This method is responsible for deletion of specific category.
     *
     * @param categoryId
     * @return Response Object with status code 204.
     */

    public void deleteSpecificCategory(String categoryId);


    /**
     * This method is responsible for deletion of specific product that present in the category.
     *
     * @param categoryId
     * @param productId
     * @return ResponseObject with status code 204.
     */

    public void deleteSpecificProduct(String categoryId, String productId);


    /**
     * This method is responsible to add a new category.
     *
     * @param categoryRequest
     * @return CategoryResponse Payload.
     */

    public CategoryResponse addCategory(CategoryRequest categoryRequest);


    /**
     * This method is responsible for adding a product into specific category.
     *
     * @param categoryId
     * @param productRequest
     * @return ProductResponse payload.
     */

    public ProductResponse addProduct(String categoryId, ProductRequest productRequest);


    /**
     * This method is responsbile for updating a specific category.
     *
     * @param categoryId
     * @param categoryRequest
     * @return CategoryResponse payload.
     */

    public CategoryResponse updateCategoryById(String categoryId, CategoryRequest categoryRequest);

}
