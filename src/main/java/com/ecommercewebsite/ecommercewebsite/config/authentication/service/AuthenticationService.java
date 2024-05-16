package com.ecommercewebsite.ecommercewebsite.config.authentication.service;

import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationRequest;
import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationResponse;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;

/**
 * Base implementation for authentication business logic
 * */
public interface AuthenticationService {
    /**
     * This is used to register a user
     * @param request This accepts user payload
     * @return a response which contains valid authentication details {@link AuthenticationResponse}
     */
    AuthenticationResponse register(UserRequest request);


    /**
     *
     * @param request is a type of {@link AuthenticationRequest} This accepts authentication Payload
     * @return a response which generates jwt token
     */

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
