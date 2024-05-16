package com.ecommercewebsite.ecommercewebsite.config.authentication.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}
