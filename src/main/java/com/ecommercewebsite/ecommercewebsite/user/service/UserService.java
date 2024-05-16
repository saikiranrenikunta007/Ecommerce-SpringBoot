package com.ecommercewebsite.ecommercewebsite.user.service;

import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleRequest;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.model.UserResponse;


import java.util.List;

public interface UserService {
    /**
     * This method is responsible for retrieve all users.
     * @return UserResponse Payload.
     */
    public  List<UserResponse> retrieveAllUsers();
    /**
     * This method is responsible for retrieve specic user details.
     *
     * @param userId
     * @return UserResponse Payload.
     */
    public UserResponse retrieveUserDetailsById(String userId);
    /**
     * This method is responsible for deleting a specific user.
     *
     * @param userId
     * @return ResponseObject with status code 204.
     */
    public void deleteUser(String userId);
    /**
     * This
     *
     * @param userId
     * @param userRequest
     * @return UserResponse Payload.
     */
    public UserResponse updateUser(UserRequest userRequest, String userId);
    /**
     * This method is responsible for retrieving all orders of a user.
     *
     * @param userId
     * @return List of OrderResponse payloads.
     */

    public List<OrderResponse> retrieveAllOrders(String userId);
}
