package com.cofomo.product.microservice.web.controller;

import com.cofomo.product.microservice.dto.UserDto;
import com.cofomo.product.microservice.mapper.MapStructMapper;
import com.cofomo.product.microservice.services.UserService;
import io.swagger.api.UserApi;
import io.swagger.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserApi {

    UserService userService;
    MapStructMapper mapper;

    @Override
    public ResponseEntity<User> addUser(User user) {
        log.info("Ajout d'une nouvelle user : " + user.toString());
        UserDto userDto = mapper.userToUserDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.userDtoToUser(userService.addUser(userDto))
        );
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(String id) {
        log.info("Suppression de la user dont l'ID est : " + id);
        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String id) {
        log.info("Envoi de la user dont l'ID est : " + id);
        User user = mapper.userDtoToUser(userService.getUserById(Long.parseLong(id)));
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        log.info("Envoi de la liste completes des users");
        List<User> users = mapper.userListDtoToUserList(userService.getUserList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }
}
