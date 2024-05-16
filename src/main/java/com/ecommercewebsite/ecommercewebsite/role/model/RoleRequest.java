package com.ecommercewebsite.ecommercewebsite.role.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RoleRequest {
    @NotNull
    @NotBlank
    private final String roleType;
    @NotNull
    @NotBlank
    private final String id;
}
