package com.ecommercewebsite.ecommercewebsite.payment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentProcessingRequest {
    private final String paymentId;
    private final String paymentRefNo;
}
