package com.ecommercewebsite.ecommercewebsite.payment.service;

import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentRequest;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {
    /**
     * This method is responsible for paymentInitiation
     * @param paymentRequest
     * @return PaymentResponse payload
     */
    PaymentResponse paymentInitiation(@RequestBody PaymentRequest paymentRequest);
}
