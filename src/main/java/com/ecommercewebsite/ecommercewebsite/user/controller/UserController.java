package com.ecommercewebsite.ecommercewebsite.user.controller;

import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.model.UserResponse;
import com.ecommercewebsite.ecommercewebsite.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@Slf4j
/**
 * This is UserController
 * This class is Responsible for all user operations.
 */
public class UserController {

    private final UserService userService;

    /**
     * This method is responsible for retrieve all users.
     *
     * @return UserResponse Payload.
     */
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> retrieveAllUsers() {
        List<UserResponse> userResponses = userService.retrieveAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);

    }

    /**
     * This method is responsible for retrieve specic user details.
     *
     * @param userId
     * @return UserResponse Payload.
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> retrieveUsersDetailsById(@PathVariable String userId) {
        UserResponse userResponse = userService.retrieveUserDetailsById(userId);
        return ResponseEntity.ok(userResponse);


    }
    /**
     * This method is responsible for deleting a specific user.
     *
     * @param userId
     * @return ResponseObject with status code 204.
     */
    @DeleteMapping("/users/{userId}")
    @PostAuthorize("authentication.principal.Id == #userId")
    public ResponseEntity<Object> deleteUserById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * This
     *
     * @param userId
     * @param userRequest
     * @return UserResponse Payload.
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable String userId, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(userRequest, userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }

    /**
     * This method is responsible for retrieving all orders of a user.
     *
     * @param userId
     * @return List of OrderResponse payloads.
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderResponse>> retrieveAllOrders(@PathVariable String userId) {
        List<OrderResponse> orderResponses = userService.retrieveAllOrders(userId);
        return ResponseEntity.ok(orderResponses);
    }

}