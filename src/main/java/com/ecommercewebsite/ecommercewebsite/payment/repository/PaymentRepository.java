package com.ecommercewebsite.ecommercewebsite.payment.repository;

import com.ecommercewebsite.ecommercewebsite.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,String> {
}
