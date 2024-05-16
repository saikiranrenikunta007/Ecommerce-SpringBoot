package com.ecommercewebsite.ecommercewebsite.config.jwt.service;

import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {


    private final long JWT_TOKEN_EXPIRATION_IN_MILLIS_SEC = 1000 * 60 * 24 * 1000;
    private static final String SECRET_KEY="244226452948404D635166546A576E5A7134743777217A25432A462D4A614E64";
    @Override
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T>claimsResolver)
    {
         Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }
    @Override
    public Claims extractAllClaims(String token)
    {
        return  Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    @Override
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }
    @Override
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    )
    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_IN_MILLIS_SEC))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    @Override
    public boolean isValidToken(String token, UserDetails userDetails)
    {
        final String userName = userDetails.getUsername();
        return extractUserName(token).equals(userName) && !isTokenExpired(token);
    }
    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    @Override
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
