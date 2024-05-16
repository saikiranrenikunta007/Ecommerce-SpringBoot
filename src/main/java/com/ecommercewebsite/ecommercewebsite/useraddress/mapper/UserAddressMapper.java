package com.ecommercewebsite.ecommercewebsite.useraddress.mapper;

import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {
    public UserAddressEntity create(UserAddressRequest userAddressRequest) {
        return UserAddressEntity.builder()
                .addressLine1(userAddressRequest.getAddressLine1())
                .addressLine2(userAddressRequest.getAddressLine2())
                .state(userAddressRequest.getState())
                .city(userAddressRequest.getCity())
                .country(userAddressRequest.getCountry())
                .build();
    }

    public UserAddressResponse toResponse(UserAddressEntity userAddressEntity) {
        return UserAddressResponse.builder()
                .id(userAddressEntity.getId())
                .addressLine1(userAddressEntity.getAddressLine1())
                .addressLine2(userAddressEntity.getAddressLine2())
                .state(userAddressEntity.getState())
                .country(userAddressEntity.getCountry())
                .city(userAddressEntity.getCity())
                .build();
    }
}
