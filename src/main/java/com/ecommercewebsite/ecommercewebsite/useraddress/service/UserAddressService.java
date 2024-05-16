package com.ecommercewebsite.ecommercewebsite.useraddress.service;

import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;

import java.util.List;

public interface UserAddressService {


    /**
     * This is UserAddressController
     * This class is responsible for user addresses.
     */
    public UserAddressResponse addAddressforUser(String userId, UserAddressRequest userAddressRequest);
    /**
     * This method is responsible for retrieve all addresses of a user.
     * @param userId
     * @return UserAddressResponse payload
     */

    public List<UserAddressResponse> retrieveAllAddressesOfUser(String userId);
    /**
     * This method is responsible for update specfic user's useraddress.
     * @param userAddressId
     * @param userAddressRequest
     * @return UserAddressResponse payload
     */

    public UserAddressResponse updateUserAddressById(String userAddressId, UserAddressRequest userAddressRequest);


    /**
     * This method is responsible for deletion of useraddress of a user.
     * @param userAddressId
     * @return
     */

    public void deleteUserAddressById(String userAddressId);
}
