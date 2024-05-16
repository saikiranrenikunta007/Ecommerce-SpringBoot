package com.ecommercewebsite.ecommercewebsite.comment.repository;

import com.ecommercewebsite.ecommercewebsite.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,String> {
}
