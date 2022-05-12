package com.cofomo.product.microservice.services.impl;

import com.cofomo.product.microservice.dao.UserDao;
import com.cofomo.product.microservice.dto.UserDto;
import com.cofomo.product.microservice.mapper.MapStructMapper;
import com.cofomo.product.microservice.model.UserEntity;
import com.cofomo.product.microservice.services.UserService;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    public final UserDao userDao;

    public final MapStructMapper mapper;

    @Override
    public List<UserDto> getUserList() {
        log.info("Service : Envoi de la liste completes des utilisateurs");
        return mapper.userListEntityToUserDtoList(userDao.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Service : Envoi de l'utilisateur dont l'ID est : " + id);
        UserEntity userEntity = userDao.findById(id).orElseThrow(()
                -> new NotFoundException(UserEntity.class, id));
        return mapper.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto addUser(UserDto user) {
        log.info("Service : Ajout d'un nouveau utilisateur  : " + user.toString());
        UserEntity userEntity = mapper.userDtoToUserEntity(user);
        return mapper.userEntityToUserDto(userDao.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Service : Suppression de l'utilisateur dont l'ID est : " + id);
        getUserById(id);
        userDao.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserEntity user = userDao.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }
    private List getAuthorities(UserEntity user) {
        String roleByUserId = user.getRole();
        final List authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleByUserId.toUpperCase()));
        return authorities;
    }
}
