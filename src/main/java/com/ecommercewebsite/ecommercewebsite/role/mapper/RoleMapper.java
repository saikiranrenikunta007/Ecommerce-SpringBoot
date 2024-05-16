package com.ecommercewebsite.ecommercewebsite.role.mapper;

import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleRequest;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleEntity create(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .roleType(roleRequest.getRoleType())
                .id(roleRequest.getId())
                .build();
    }

    public RoleResponse toResponse(RoleEntity roleEntity) {
        return RoleResponse.builder()
                .roleType(roleEntity.getRoleType())
                .build();
    }
}
