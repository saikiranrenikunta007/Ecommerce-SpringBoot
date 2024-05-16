package com.ecommercewebsite.ecommercewebsite.payment.mapper;

import com.ecommercewebsite.ecommercewebsite.payment.entity.PaymentEntity;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentRequest;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMapper {
    public PaymentEntity create(PaymentRequest paymentRequest) {
        return PaymentEntity.builder()
                .gst(paymentRequest.getGst())
                .pgCharge(paymentRequest.getPgCharge())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .paymentRefNo(UUID.randomUUID().toString())
                .totalAmount(paymentRequest.getGst()+ paymentRequest.getPgCharge()+ paymentRequest.getAmount())
                .build();
    }

    public PaymentResponse toResponse(PaymentEntity paymentEntity) {
        return PaymentResponse.builder()
                .paymentId(paymentEntity.getId())
                .paymentRefNo(paymentEntity.getPaymentRefNo())
                .status(paymentEntity.getStatus())
                .build();

    }
}
