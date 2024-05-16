package com.ecommercewebsite.ecommercewebsite.useraddress.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
public class UserAddressRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp="[a-zA-z\s]+")
    private  final String addressLine1;
    @NotBlank
    @NotNull
    @Pattern(regexp = "[a-zA-z\s]+")
    private final String addressLine2;
    @NotNull
    @NotBlank
    private final String city;
    @NotNull
    @NotBlank
    private final String state;
    @NotNull
    @NotBlank
    private final String country;
}
