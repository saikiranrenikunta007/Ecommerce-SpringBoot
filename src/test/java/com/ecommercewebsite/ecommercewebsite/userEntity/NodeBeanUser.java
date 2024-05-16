package com.ecommercewebsite.ecommercewebsite.userEntity;

import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NodeBeanUser {
    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private RoleResponse roleResponse;
}
