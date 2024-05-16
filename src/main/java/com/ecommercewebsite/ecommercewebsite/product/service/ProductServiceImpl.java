package com.ecommercewebsite.ecommercewebsite.product.service;

import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.order.repository.OrderRepository;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.mapper.ProductMapper;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    @Override
    public ProductResponse addNewProduct(ProductRequest productRequest) {
       ProductEntity productEntity = productMapper.create(productRequest);
       productRepository.save(productEntity);
       return productMapper.toResponse(productEntity);
    }
    @Override
    public ProductResponse retrieveProductById(String productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return productMapper.toResponse(productEntity.get());
    }

    @Override
    public ProductResponse updateProductById(String productId,ProductRequest productRequest) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if(product.isEmpty())  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ProductEntity productEntity = productMapper.create(productRequest);
        productEntity.setCategoryEntity(product.get().getCategoryEntity());
        productEntity.setId(productId);
        productRepository.save(productEntity);
        return productMapper.toResponse(productEntity);

    }

    @Override
    public ProductResponse addProductById(String orderId,ProductRequest productRequest) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isPresent())
        {
            ProductEntity productEntity = productMapper.create(productRequest);
            if(productEntity.getOrderEntities()==null)
            {
                productEntity.setOrderEntities(new HashSet<>(List.of(orderEntity.get())));
            }
            else
            {
                productEntity.getOrderEntities().add(orderEntity.get());
            }

            productRepository.save(productEntity);
            orderEntity.get().getProductEntities().add(productEntity);
            orderRepository.save(orderEntity.get());
            return productMapper.toResponse(productEntity);
        }
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<ProductEntity> retrieveProductsWithAscendingOrder(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public List<ProductEntity> retrieveProductsWithDescendingOrder(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    @Override
    public Page<ProductEntity> retrieveProductsWithPaginationAscendingOrder(int offset, int size, String field) {
        return productRepository.findAll(PageRequest.of(offset,size).withSort(Sort.by(Sort.Direction.ASC,field)));
    }

    @Override
    public Page<ProductEntity> retrieveProductsWithPaginationDescendingOrder(int offset, int size, String field) {
        return productRepository.findAll(PageRequest.of(offset,size).withSort(Sort.by(Sort.Direction.DESC,field)));
    }
    @Override
    public List<ProductResponse> retrieveAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toResponse).toList();
    }
    @Override
    public Page<ProductEntity> retrieveAllProductsByName(String name,int offset, int size, String field)
    {
        Pageable pageable = PageRequest.of(offset,size).withSort(Sort.by(Sort.Direction.DESC,field));
        return productRepository.findByName(name,pageable);
    }
}
