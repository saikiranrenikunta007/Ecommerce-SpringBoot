package com.ecommercewebsite.ecommercewebsite.useraddress.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_address")
public class UserAddressEntity extends AbstractAuditingEntity {
    @Column(name="address_line1")
    private String addressLine1;
    @Column(name="address_line2")
    private String addressLine2;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "userId")
    private UserEntity userEntity;
    @JsonIgnore
    @OneToMany(mappedBy = "userAddressEntity")
    private List<OrderEntity> orderEntityDetails =new ArrayList<>();
}
