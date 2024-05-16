package com.ecommercewebsite.ecommercewebsite.config.authentication.service;

import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationRequest;
import com.ecommercewebsite.ecommercewebsite.config.authentication.model.AuthenticationResponse;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.user.mapper.UserMapper;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public AuthenticationResponse register(UserRequest request) {
        //s
        UserEntity userEntity =  userMapper.create(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //log.info("started");
        try{
            Authentication val = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmailId(),
                    request.getPassword()

            ));
           log.info(val.toString());
           System.out.println("All:"+"name="+val.getName());
            System.out.println("All:"+"authorities="+val.getAuthorities());
            System.out.println("All:"+"authenticated="+val.isAuthenticated());
            System.out.println("All:"+"details="+val.getDetails());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage()+"    *     "+e.getStackTrace());
        }
        var user = userRepository.findByEmailId(request.getEmailId())
                .orElseThrow();

        //log.info(user.toString());
        //log.info("found email");
        //System.out.println("Authorities: "+user.getAuthorities());
        var jwtToken = jwtService.generateToken(user);
        //log.info(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
