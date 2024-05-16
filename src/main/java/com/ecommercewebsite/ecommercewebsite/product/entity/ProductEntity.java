package com.ecommercewebsite.ecommercewebsite.product.entity;

import com.ecommercewebsite.ecommercewebsite.category.entity.CategoryEntity;
import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.comment.entity.CommentEntity;
import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="product")
public class ProductEntity extends AbstractAuditingEntity {

    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="image_ur11")
    private String imageUrl1;
    @Column(name="image_url2")
    private String imageUrl2;
    @Column(name="price")
    private double price;
    @Column(name="quantity")
    private int quantity;
    @Column(name="rating")
    private double rating;
    @Column(name="is_delivery_available")
    private boolean isDeliveryAvailable;
    @Column(name="manufacturerInfo")
    private String manufacturerInfo;
    @ManyToOne
    @JoinColumn(name="userAddressId")
    private UserAddressEntity userAddressEntity;

    @ManyToMany(mappedBy = "productEntities")
    private Set<OrderEntity> orderEntities =new HashSet<>();
    @ManyToOne
    @JoinColumn(name="categoryId")
    private CategoryEntity categoryEntity;
    @OneToMany(mappedBy = "productEntity")
    private List<CommentEntity> commentEntity;
    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl1='" + imageUrl1 + '\'' +
                ", imageUrl2='" + imageUrl2 + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", isDeliveryAvailable=" + isDeliveryAvailable +
                ", manufacturerInfo='" + manufacturerInfo + '\'' +
                '}';
    }
}
