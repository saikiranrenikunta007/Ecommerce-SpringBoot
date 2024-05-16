package com.ecommercewebsite.ecommercewebsite.useraddress.controller;

import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

/**
 * This is UserAddressController
 * This class is responsible for user addresses.
 */

@RestController
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    /**
     * This method is responsible for adding address for a user
     * @param userId
     * @param userAddressRequest
     * @return UserAddressResponse payload.
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @PostMapping("/users/{userId}/user-addresses")
    public ResponseEntity<UserAddressResponse> addAddressforUser(@PathVariable String userId, @Valid @RequestBody UserAddressRequest userAddressRequest)
    {
        UserAddressResponse userAddressResponse = userAddressService.addAddressforUser(userId,userAddressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAddressResponse);
    }

    /**
     * This method is responsible for retrieve all addresses of a user.
     * @param userId
     * @return UserAddressResponse payload
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @GetMapping("/users/{userId}/user-addresses")
    public ResponseEntity<List<UserAddressResponse>> retrieveAllAddressesOfUser(@PathVariable String userId)
    {
        List<UserAddressResponse> userAddressResponses = userAddressService.retrieveAllAddressesOfUser(userId);
        return ResponseEntity.ok(userAddressResponses);
    }

    /**
     * This method is responsible for update specfic user's useraddress.
     * @param userAddressId
     * @param userAddressRequest
     * @return UserAddressResponse payload
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @PutMapping("/users/{userId}/user-addresses/{userAddressId}")
    public ResponseEntity<UserAddressResponse> updateUserAddressById(@PathVariable String userId,@PathVariable String userAddressId,@Valid @RequestBody UserAddressRequest userAddressRequest)
    {

        UserAddressResponse userAddressResponse = userAddressService.updateUserAddressById(userAddressId,userAddressRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userAddressResponse);
    }

    /**
     * This method is responsible for deletion of useraddress of a user.
     * @param userAddressId
     * @return
     */
    @PostAuthorize("authentication.principal.Id == #userId")
    @DeleteMapping("/users/{userId}/user-addresses/{userAddressId}")
    public ResponseEntity<Object> deleteUserAddressById(@PathVariable String userId,@PathVariable String userAddressId)
    {
        userAddressService.deleteUserAddressById(userAddressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
