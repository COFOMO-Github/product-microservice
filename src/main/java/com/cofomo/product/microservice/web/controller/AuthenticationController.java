package com.cofomo.product.microservice.web.controller;

import com.cofomo.product.microservice.dao.UserDao;
import com.cofomo.product.microservice.dto.UserDto;
import com.cofomo.product.microservice.dto.AuthenticationResponseDto;
import com.cofomo.product.microservice.model.UserEntity;
import com.cofomo.product.microservice.services.impl.UserServiceImpl;
import com.cofomo.product.microservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private UserDao userDao;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponseDto> createAuthenticationToken(@RequestBody UserDto authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        UserEntity userEntity = userDao.findByUsername(userDetails.getUsername()).orElseThrow();

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(userEntity.getId(), userDetails.getUsername(), jwt));
    }
}
