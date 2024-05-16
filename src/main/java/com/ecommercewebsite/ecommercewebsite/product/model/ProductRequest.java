package com.ecommercewebsite.ecommercewebsite.product.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductRequest {

    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z a-z]{3,20}")
    private final String name;
    @Pattern(regexp="[A-Z a-z]*[0-9]*")
    private final String description;
    @Pattern(regexp = "^(http(s):\\/\\/.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$")
    private final String imageUrl1;
    @Pattern(regexp = "^(http(s):\\/\\/.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$")
    private final String imageUrl2;
    @NotNull
    private final double price;
    @NotNull
    private final int quantity;
    @NotNull
    private final double rating;
    @BooleanFlag
    private final boolean isDeliveryAvailable;
    String manufacturerInfo;

}
