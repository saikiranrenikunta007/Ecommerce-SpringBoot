package com.ecommercewebsite.ecommercewebsite.comment.mapper;

import com.ecommercewebsite.ecommercewebsite.comment.entity.CommentEntity;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentEntity create(CommentRequest commentRequest) {
        return CommentEntity.builder()
                .reviews(commentRequest.getReviews())
                .build();
    }

    public CommentResponse toResponse(CommentEntity commentEntity) {
        return CommentResponse.builder().id(commentEntity.getId()).reviews(commentEntity.getReviews()).build();
    }
}
