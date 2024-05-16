package com.ecommercewebsite.ecommercewebsite.category.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="category")
public class CategoryEntity extends AbstractAuditingEntity {

    @Column(name="name")
    private  String name;
    @Column(name="description")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "categoryEntity",cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities =new ArrayList<>();
}
