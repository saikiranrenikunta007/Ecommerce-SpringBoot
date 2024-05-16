package com.ecommercewebsite.ecommercewebsite.product.controller;

import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.model.PagingResponse;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * This method is responsible for add new product in the repository.
     *
     * @param productRequest
     * @return ProductResponse payload
     */
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addNewProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addNewProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    /**
     * This method is responsible for retrieve the specific product.
     *
     * @param productId
     * @return productResponse Payload
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> retrieveProductById(@PathVariable String productId) {
        ProductResponse productResponse = productService.retrieveProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    /**
     * This method is responsible for update the specific product.
     *
     * @param productId
     * @param productRequest
     * @return ProductResponse Payload
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable String productId, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProductById(productId, productRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponse);
    }

    /**
     * This method is responsible for add a product into order.
     *
     * @param orderId
     * @param productRequest
     * @return ProductResponse Payload.
     */
    @PostMapping("/products/orders/{orderId}")
    public ResponseEntity<ProductResponse> addAProductById(@PathVariable String orderId, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProductById(orderId, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    /**
     * This method is responsible for retrievingProducts in ascending order.
     *
     * @param field
     * @return Page of Products payload
     */
    @GetMapping("/products/ascending/{field}")
    public PagingResponse<List<ProductEntity>> retrieveProductsWithAscendingOrder(@PathVariable String field) {
        List<ProductEntity> productEntities = productService.retrieveProductsWithAscendingOrder(field);
        return new PagingResponse<>(productEntities.size(), productEntities);
    }

    /**
     * This method is responsible for retrievingProducts in descending order.
     *
     * @param field
     * @return Page of Products payload
     */
    @GetMapping("/products/descending/{field}")
    public PagingResponse<List<ProductEntity>> retrieveProductsWithDescendingOrder(@PathVariable String field) {
        List<ProductEntity> productEntities = productService.retrieveProductsWithDescendingOrder(field);
        return new PagingResponse<>(productEntities.size(), productEntities);
    }

    /**
     * This method is responsible for retrievingProducts in ascending order with pagination.
     *
     * @param size
     * @param offset
     * @param field
     * @return Page of Products payload
     */
    @GetMapping("/products/pagination-ascending/{offset}/{size}/{field}")
    public PagingResponse<Page<ProductEntity>> retrieveProductsWithPaginationAscendingOrder(@PathVariable int offset, @PathVariable int size, @PathVariable String field) {
        Page<ProductEntity> productEntities = productService.retrieveProductsWithPaginationAscendingOrder(offset, size, field);
        return new PagingResponse<>(productEntities.getSize(), productEntities);
    }

    /**
     * This method is responsible for retrievingProducts in descending order with pagination.
     *
     * @param offset
     * @param size
     * @param field
     * @return Page of Products payload
     */

    @GetMapping("/products/pagination-descending/{offset}/{size}/{field}")
    public PagingResponse<Page<ProductEntity>> retrieveProductsWithPaginationDescendingOrder(@PathVariable int offset, @PathVariable int size, @PathVariable String field) {
        Page<ProductEntity> productEntities = productService.retrieveProductsWithPaginationDescendingOrder(offset, size, field);
        return new PagingResponse<>(productEntities.getSize(), productEntities);
    }

    /**
     * This method is responsible for retrieving all products.
     *
     * @return Paging response of list of products.
     */
    @GetMapping("/products")
    public PagingResponse<List<ProductResponse>> retrieveAllProducts() {
        List<ProductResponse> products = productService.retrieveAllProducts();
        return new PagingResponse<>(products.size(), products);
    }

    /**
     * This method is for retrieving all productsbyname with pagination, filter and sorting.
     *
     * @param name
     * @param offset
     * @param size
     * @param field
     * @return PageResponse of products
     */
    @GetMapping("/products-pagination")
    public PagingResponse<Page<ProductEntity>> retrieveAllProductsByName(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "offset") int offset, @RequestParam(value = "size") int size, @RequestParam(value = "field") String field) {
        Page<ProductEntity> productEntities = productService.retrieveAllProductsByName(name, offset, size, field);
        return new PagingResponse<>(productEntities.getSize(), productEntities);
    }
}
