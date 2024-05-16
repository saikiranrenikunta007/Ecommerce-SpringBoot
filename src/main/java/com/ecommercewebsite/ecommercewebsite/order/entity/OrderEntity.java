package com.ecommercewebsite.ecommercewebsite.order.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.payment.entity.PaymentEntity;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="orders")
public class OrderEntity extends AbstractAuditingEntity {
    private String orderRefNo;
    private String deliveryStatus;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userAddressId")
    private UserAddressEntity userAddressEntity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity userEntity;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = { @JoinColumn(name = "orderId") },
            inverseJoinColumns = { @JoinColumn(name = "productId") })
    private Set<ProductEntity> productEntities = new HashSet<>();
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="paymentId")
    private PaymentEntity paymentEntity;

}
