package com.cofomo.product.microservice.web.controller;

import com.cofomo.product.microservice.dao.UserDao;
import com.cofomo.product.microservice.model.UserEntity;
import com.cofomo.product.microservice.services.impl.UserServiceImpl;
import com.cofomo.product.microservice.utils.JwtUtil;
import io.swagger.api.AuthenticationApi;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController  implements AuthenticationApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<LoginResponse> authentication(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
        UserEntity userEntity = userDao.findByUsername(userDetails.getUsername()).orElseThrow();

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        LoginResponse response = new LoginResponse();
        response.setId(String.valueOf(userEntity.getId()));
        response.setUsername(userDetails.getUsername());
        response.setRole(userEntity.getRole());
        response.setToken(jwt);

        return ResponseEntity.ok(response);
    }

}
