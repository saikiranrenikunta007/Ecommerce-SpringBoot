package com.ecommercewebsite.ecommercewebsite.config.jwt.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public interface JwtService {
    public String extractUserName(String jwt);

    Claims extractAllClaims(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims,
                         UserDetails userDetails
    );

    boolean isValidToken(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    Key getSignInKey();
}
