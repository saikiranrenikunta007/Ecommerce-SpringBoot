package com.ecommercewebsite.ecommercewebsite.config.authentication.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    private String emailId;
    private String password;
}
