package com.ecommercewebsite.ecommercewebsite.product.service;

import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * This method is responsible for add new product in the repository.
     *
     * @param productRequest
     * @return ProductResponse payload
     */
    public ProductResponse addNewProduct(ProductRequest productRequest);

    /**
     * This method is responsible for retrieve the specific product.
     *
     * @param productId
     * @return productResponse Payload
     */

    public ProductResponse retrieveProductById(String productId);

    /**
     * This method is responsible for update the specific product.
     *
     * @param productId
     * @param productRequest
     * @return ProductResponse Payload
     */
    public ProductResponse updateProductById(String productId, ProductRequest productRequest);

    /**
     * This method is responsible for add a product into order.
     *
     * @param orderId
     * @param productRequest
     * @return ProductResponse Payload.
     */

    public ProductResponse addProductById(String orderId, ProductRequest productRequest);

    /**
     * This method is responsible for retrievingProducts in ascending order.
     *
     * @param field
     * @return Page of Products payload
     */
    public List<ProductEntity> retrieveProductsWithAscendingOrder(String field);

    /**
     * This method is responsible for retrievingProducts in descending order.
     *
     * @param field
     * @return Page of Products payload
     */

    public List<ProductEntity> retrieveProductsWithDescendingOrder(String field);

    /**
     * This method is responsible for retrievingProducts in ascending order with pagination.
     *
     * @param size
     * @param offset
     * @param field
     * @return Page of Products payload
     */

    public Page<ProductEntity> retrieveProductsWithPaginationAscendingOrder(int offset, int size, String field);

    /**
     * This method is responsible for retrievingProducts in descending order with pagination.
     *
     * @param offset
     * @param size
     * @param field
     * @return Page of Products payload
     */

    public Page<ProductEntity> retrieveProductsWithPaginationDescendingOrder(int offset, int size, String field);

    /**
     * This method is responsible for retrieving all products.
     *
     * @return Paging response of list of products.
     */

    public List<ProductResponse> retrieveAllProducts();

    /**
     * This method is for retrieving all productsbyname with pagination, filter and sorting.
     *
     * @param name
     * @param offset
     * @param size
     * @param field
     * @return PageResponse of products
     */
    Page<ProductEntity> retrieveAllProductsByName(String name, int offset, int size, String field);
}
