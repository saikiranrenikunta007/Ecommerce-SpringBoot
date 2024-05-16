package com.ecommercewebsite.ecommercewebsite.category.service;
import com.ecommercewebsite.ecommercewebsite.category.entity.CategoryEntity;
import com.ecommercewebsite.ecommercewebsite.category.mapper.CategoryMapper;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryResponse;
import com.ecommercewebsite.ecommercewebsite.category.repository.CategoryRepository;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.repository.ProductRepository;
import com.ecommercewebsite.ecommercewebsite.product.mapper.ProductMapper;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final CategoryMapper mapper;
    private final ProductMapper productMapper;

    public List<CategoryResponse> retrieveAllCategories() {
        List<CategoryResponse> categoryResponses = categoryRepository.findAll().stream().map(mapper ::toResponse).toList();
        if(categoryResponses.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return categoryResponses;

    }

    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = mapper.create(categoryRequest);
        categoryRepository.save(categoryEntity);
        return mapper.toResponse(categoryEntity);
    }

    public List<ProductResponse> retrieveSpecificCategoryProducts(String categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if(categoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<ProductResponse> productResponses = categoryEntity.get().getProductEntities().stream().map(productMapper::toResponse).toList();
        if(productResponses.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return productResponses;
    }
    public ProductResponse retrieveSpecificProduct(String categoryId, String productId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if(category.isEmpty())
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<ProductEntity> productEntities = category.get().getProductEntities();
        if(productEntities ==null)
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<ProductEntity> product = productEntities.stream().filter(q->q.getId().equals(productId)).findFirst();
        if(product.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        return  productMapper.toResponse(product.get());
    }
    public void deleteSpecificProduct(String categoryId,String productId)
    {
        Optional<CategoryEntity>categoryEntity = categoryRepository.findById(categoryId);
        if(categoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<ProductEntity> productEntities =categoryEntity.get().getProductEntities();
        if(productEntities.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Predicate<? super ProductEntity> predicate = p -> p.getId().equalsIgnoreCase(productId);
        productEntities.removeIf(predicate);
        productRepository.deleteById(productId);
    }
    public void deleteAllCategories()
    {
        if(categoryRepository.findAll().isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        categoryRepository.deleteAll();
    }
    public void deleteSpecificCategory(String categoryId)
    {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if(categoryEntity.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        categoryRepository.deleteById(categoryId);
    }

    public ProductResponse addProduct(String categoryId, ProductRequest productRequest)
    {
           Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
           if(categoryEntity.isEmpty())
           {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
           }
           ProductEntity productEntity = productMapper.create(productRequest);
           productEntity.setCategoryEntity(categoryEntity.get());
           productRepository.save(productEntity);
           return productMapper.toResponse(productEntity);

    }
    public CategoryResponse updateCategoryById(String categoryId, CategoryRequest categoryRequest)
    {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
        {
            CategoryEntity categoryEntity = mapper.create(categoryRequest);
            categoryEntity.setId(categoryId);
            categoryEntity.setProductEntities(category.get().getProductEntities());
            categoryRepository.save(categoryEntity);
            return mapper.toResponse(categoryEntity);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
