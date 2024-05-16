package com.ecommercewebsite.ecommercewebsite.payment.model;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@RequiredArgsConstructor
@Setter
@Getter
public class PaymentResponse {
    private final String paymentId;
    private final String paymentRefNo;
    private final String status;
}
