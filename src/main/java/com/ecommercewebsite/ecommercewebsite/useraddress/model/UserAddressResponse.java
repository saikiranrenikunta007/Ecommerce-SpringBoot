package com.ecommercewebsite.ecommercewebsite.useraddress.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class UserAddressResponse {
    private final String id;
    private  final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
}
