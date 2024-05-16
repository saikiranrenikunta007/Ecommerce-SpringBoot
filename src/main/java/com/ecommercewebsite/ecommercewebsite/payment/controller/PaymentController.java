package com.ecommercewebsite.ecommercewebsite.payment.controller;

import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentRequest;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentResponse;
import com.ecommercewebsite.ecommercewebsite.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * This method is responsible for paymentInitiation
     * @param paymentRequest
     * @return PaymentResponse payload
     */

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> paymentInitiation(@Valid @RequestBody PaymentRequest paymentRequest)
    {
        PaymentResponse paymentResponse = paymentService.paymentInitiation(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }
}
