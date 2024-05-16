package com.ecommercewebsite.ecommercewebsite.role.model;
import lombok.*;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class RoleResponse {
    private final String roleType;
}
