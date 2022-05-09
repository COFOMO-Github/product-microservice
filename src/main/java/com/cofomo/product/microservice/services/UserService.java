package com.cofomo.product.microservice.services;

import com.cofomo.product.microservice.dto.UserDto;
import com.cofomo.product.microservice.model.UserEntity;
import com.cofomo.product.microservice.web.exception.NotFoundException;

import java.util.List;

public interface UserService {

    public List<UserDto> getUserList();


    public UserDto getUserById(Long id);


    public UserDto addUser(UserDto user);


    public void deleteUser(Long id);
//
//
//    public UserDto loadUserByUsername(String username);
}
