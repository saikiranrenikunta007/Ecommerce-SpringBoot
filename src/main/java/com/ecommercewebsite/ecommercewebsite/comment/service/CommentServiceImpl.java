package com.ecommercewebsite.ecommercewebsite.comment.service;

import com.ecommercewebsite.ecommercewebsite.comment.entity.CommentEntity;
import com.ecommercewebsite.ecommercewebsite.comment.mapper.CommentMapper;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;
import com.ecommercewebsite.ecommercewebsite.comment.repository.CommentRepository;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.repository.ProductRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
   private  final CommentRepository commentRepository;
   private final CommentMapper commentMapper;
   private final ProductRepository productRepository;
   @Override
    public CommentResponse addComment(CommentRequest commentRequest,String productId)
    {

        CommentEntity commentEntity = commentMapper.create(commentRequest);
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent())
        {
            commentEntity.setProductEntity(productEntity.get());
            commentRepository.save(commentEntity);
            return commentMapper.toResponse(commentEntity);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    @Override
    public List<CommentResponse> retrieveAllComments(String productId)
    {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent())
        {
            return productEntity.get().getCommentEntity().stream().map(commentMapper::toResponse).toList();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @Override
    public CommentResponse updateCommentById(CommentRequest commentRequest,String commentId)
    {


        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
        if(commentEntity.isPresent())
        {
            CommentEntity comment = commentMapper.create(commentRequest);
            comment.setId(commentId);
            comment.setProductEntity(commentEntity.get().getProductEntity());
            commentRepository.save(comment);
            return commentMapper.toResponse(comment);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    @Override
    public CommentResponse deleteCommentById(String commentId)
    {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
        if(commentEntity.isPresent())
        {
            commentRepository.deleteById(commentId);
            return commentMapper.toResponse(commentEntity.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<CommentResponse> deleteAllCommentOfProduct(String productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent())
        {
            List<CommentResponse> commentResponses = productEntity.get().getCommentEntity().stream().map(commentMapper::toResponse).toList();
            Predicate<? super CommentEntity> predicate = x -> x.getProductEntity().getId().equals(productId);
            productEntity.get().getCommentEntity().stream().filter(predicate).forEach(commentRepository::delete);
            return commentResponses;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

