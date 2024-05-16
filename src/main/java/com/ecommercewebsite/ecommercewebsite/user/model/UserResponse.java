package com.ecommercewebsite.ecommercewebsite.user.model;

import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class UserResponse {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String mobileNumber;
    private final String emailId;
    private final RoleResponse roleResponse;


}
