package com.ecommercewebsite.ecommercewebsite.comment.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="comment")
public class CommentEntity extends AbstractAuditingEntity {
    @Column(name="reviews")
    private String reviews;
    @ManyToOne
    @JoinColumn(name="product")
    private ProductEntity productEntity;

}
