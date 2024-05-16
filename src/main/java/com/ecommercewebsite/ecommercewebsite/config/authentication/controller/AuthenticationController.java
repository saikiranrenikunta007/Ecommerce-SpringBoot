package com.ecommercewebsite.ecommercewebsite.config.authentication.controller;

import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationRequest;
import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationResponse;
import com.ecommercewebsite.ecommercewebsite.config.authentication.service.AuthenticationService;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type AuthenticationController
 * Theis class is responsible to perform actions on security primarily register, validate
 *
 * @see <a href=""></a>
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**id
     * This is used to register a user
     *
     * @param request is type of {@link UserRequest} This accepts user payload
     * @return a response which contains valid authentication details
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRequest request) {
        log.info("AuthenticationController::register started with input {}", request);
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        return ResponseEntity.ok(authenticationResponse);
    }

    /**
     *
     * @param request is a type of {@link AuthenticationRequest} This accepts authentication Payload
     * @return a response which generates jwt token
     */

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }

}
