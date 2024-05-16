package com.ecommercewebsite.ecommercewebsite.payment.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
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
@Table(name="payment")
public class PaymentEntity extends AbstractAuditingEntity {
    @Column(name="amount")
    private double amount;
    @Column(name="gst")
    private double gst;
    @Column(name="pg_charge")
    private double pgCharge;
    @Column(name="total_amount")
    private double totalAmount;
    @Column(name="payment_ref_no")
    private String paymentRefNo;
    @Column(name="payment_method")
    private String paymentMethod;
    @Column(name="status")
    private String status;
    @JsonIgnore
    @OneToMany(mappedBy = "paymentEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntityDetails =new ArrayList<>();
}
