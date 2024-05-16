package com.ecommercewebsite.ecommercewebsite.apiintegration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiIntegrationRequest {
    @NotNull
    private String name;
    @NotNull
    private String username;
    @Email
    private String email;
}
