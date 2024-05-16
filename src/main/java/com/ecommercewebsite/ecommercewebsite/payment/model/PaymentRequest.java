package com.ecommercewebsite.ecommercewebsite.payment.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentRequest {

        private final String orderId;
        private final double gst;
        private final double pgCharge;
        private final double amount;
        private final String paymentMethod;
}


