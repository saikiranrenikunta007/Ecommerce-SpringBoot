package com.ecommercewebsite.ecommercewebsite.user.mapper;
import com.ecommercewebsite.ecommercewebsite.role.mapper.RoleMapper;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;
    public UserEntity create(UserRequest userRequest){
        return UserEntity.builder().mobileNumber(userRequest.getMobileNumber())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .roleEntity(userRequest.getRoleEntity())
                .build();
    }
    public UserResponse toResponse(UserEntity userEntity)
    {
        return UserResponse.builder()
                .id(userEntity.getId())
                .mobileNumber(userEntity.getMobileNumber())
                .dateOfBirth(userEntity.getDateOfBirth())
                .emailId(userEntity.getEmailId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .roleResponse(roleMapper.toResponse(userEntity.getRoleEntity()))
                .build();
    }
}